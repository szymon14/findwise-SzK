package com.findwise.service;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.findwise.data.Document;
import com.findwise.data.DocumentApiRequest;
import com.findwise.data.IndexDocumentEntry;
import com.findwise.repository.SearchEngineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchEngineService implements SearchEngine {

    private final SearchEngineRepository repository;

    public SearchEngineService(SearchEngineRepository repository) {
        this.repository = repository;
    }

    public void indexDocuments(List<DocumentApiRequest> request) {
        request.forEach(r -> indexDocument(r.getId(), r.getContent()));
    }

    @Override
    public void indexDocument(String id, String content){
        List<String> myList = new ArrayList<>(Arrays.asList(content.split(" ")));
        Document documentToSave = new Document(id, myList);
        repository.save(documentToSave);
    }

    @Override
    public List<IndexEntry> search(String term) {
        List<Document> documentList = repository.findAll();
        List<Document> filteredDocList = documentList.stream().filter(
                        document -> document.getContent().stream().anyMatch(term::equalsIgnoreCase))
                .collect(Collectors.toList());
        double idf = Math.log((double) documentList.size() / filteredDocList.size());//calculate idf
        return filteredDocList.stream().map(document -> documentToIndexEntry(document, term, idf))
                .sorted(Comparator.comparingDouble(IndexEntry::getScore))
                .collect(Collectors.toList());
    }

    private IndexEntry documentToIndexEntry(Document doc, String term, double idf) {
        IndexEntry entry = new IndexDocumentEntry();
        entry.setId(doc.getId());
        double score = TfCalculate(doc.getContent(), term) * idf;
        entry.setScore(score);
        return entry;
    }

    private double TfCalculate(List<String> stringList, String term) {
        int length = stringList.size();
        int count = (int) stringList.stream().filter(term::equalsIgnoreCase).count();
        return (double) count / length;
    }

}
