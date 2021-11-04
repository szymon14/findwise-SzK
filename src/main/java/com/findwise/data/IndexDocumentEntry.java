package com.findwise.data;

import com.findwise.IndexEntry;

public class IndexDocumentEntry implements IndexEntry {

    private String id;
    private double score;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }
}
