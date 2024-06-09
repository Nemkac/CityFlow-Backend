package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus save(Bus bus);
    List<Bus> findAll();
    Optional<Bus> findById(Integer id);
    void deleteById(Integer id);
}
