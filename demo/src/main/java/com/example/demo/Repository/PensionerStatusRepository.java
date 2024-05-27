package com.example.demo.Repository;

import com.example.demo.Model.PensionerStatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PensionerStatusRepository extends JpaRepository<PensionerStatusRequest,Integer> {

    PensionerStatusRequest save(PensionerStatusRequest pensionerStatusRequest);
    Optional<PensionerStatusRequest> findByUsername(String username);
    Optional<PensionerStatusRequest> findByName(String name);
    PensionerStatusRequest findByNameAndUsername(String name, String username);
    PensionerStatusRequest[] findAllByUsername(String username);
    List<PensionerStatusRequest> findAll();
}
