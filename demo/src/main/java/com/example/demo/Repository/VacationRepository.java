package com.example.demo.Repository;

import com.example.demo.Model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacationRepository extends JpaRepository<VacationRequest,Integer> {

    VacationRequest save(VacationRequest vacationRequest);
    Optional<VacationRequest> findByUsername(String username);
    Optional<VacationRequest> findByName(String name);
    VacationRequest findByNameAndUsername(String name, String username);
    VacationRequest[] findAllByUsername(String username);
    List<VacationRequest> findAll();
}
