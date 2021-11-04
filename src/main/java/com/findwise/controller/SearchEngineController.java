package com.findwise.controller;

import com.findwise.IndexEntry;
import com.findwise.data.DocumentApiRequest;
import com.findwise.service.SearchEngineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchEngineController {

    private final SearchEngineService service;

    public SearchEngineController(SearchEngineService service) {
        this.service = service;
    }

    @ApiOperation(value = "getIndexForTerm", nickname = "getIndexForTermGet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 201, message = "Created", response = List.class),
            @ApiResponse(code = 400, message = "System error", response = List.class)
    })
    @RequestMapping(value = "/{term}",
        produces =  {"application/json"},
        method = RequestMethod.GET)
    public ResponseEntity<List<IndexEntry>> getIndexesForTerm(@PathVariable String term) {
        return new ResponseEntity<>(service.search(term), HttpStatus.OK);
    }

    @ApiOperation(value = "insertDocument", nickname = "insertDocumentPost")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 201, message = "Created", response = List.class),
            @ApiResponse(code = 400, message = "System error", response = List.class)
    })
    @RequestMapping(value = "/enterDocument",
            produces =  {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<String> insertDocument(@RequestBody DocumentApiRequest request) {
        service.indexDocument(request.getId(), request.getContent());
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @ApiOperation(value = "insertDocuments", nickname = "insertDocumentsPost")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 201, message = "Created", response = List.class),
            @ApiResponse(code = 400, message = "System error", response = List.class)
    })
    @RequestMapping(value = "/enterDocuments",
            produces =  {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<String> insertDocuments(@RequestBody List<DocumentApiRequest> request) {
        service.indexDocuments(request);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

}
