package com.findwise.repository;

import com.findwise.data.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchEngineRepository extends JpaRepository<Document,String> {
}
