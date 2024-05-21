package com.example.demo.Repository;

import com.example.demo.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Integer> {

    Document save(Document document);
    Optional<Document> findByUsername(String username);
    Optional<Document> findByName(String name);
    Document findByNameAndUsername(String name, String username);


}
