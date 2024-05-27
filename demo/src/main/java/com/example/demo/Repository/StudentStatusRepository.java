package com.example.demo.Repository;

import com.example.demo.Model.StudentStatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentStatusRepository extends JpaRepository<StudentStatusRequest,Integer> {

    StudentStatusRequest save(StudentStatusRequest studentStatusRequest);
    Optional<StudentStatusRequest> findByUsername(String username);
    Optional<StudentStatusRequest> findByName(String name);
    StudentStatusRequest findByNameAndUsername(String name, String username);
    StudentStatusRequest[] findAllByUsername(String username);
    List<StudentStatusRequest> findAll();
}
