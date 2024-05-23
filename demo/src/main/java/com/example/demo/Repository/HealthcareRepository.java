package com.example.demo.Repository;

import com.example.demo.Model.HealthcareRequest;
import com.example.demo.Model.StudentStatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthcareRepository extends JpaRepository<HealthcareRequest,Integer> {

    HealthcareRequest save(HealthcareRequest healthcareRequest);
    Optional<HealthcareRequest> findByUsername(String username);
    Optional<HealthcareRequest> findByName(String name);
    HealthcareRequest findByNameAndUsername(String name, String username);
    HealthcareRequest[] findAllByUsername(String username);
    List<HealthcareRequest> findAll();
}
