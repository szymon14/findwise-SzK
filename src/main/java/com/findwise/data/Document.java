package com.findwise.data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity(name = "Document")
public class Document {
    @Id
    private String id;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> content;

    public Document(String id, List<String> content) {
        this.id = id;
        this.content = content;
    }

    public Document() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
