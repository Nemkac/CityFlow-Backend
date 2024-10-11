package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.ElectricBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricBusRepository extends JpaRepository<ElectricBus, Integer> {
    ElectricBus save(ElectricBus bus);
    List<ElectricBus> findAll();
    boolean existsByBus(Bus bus);
    ElectricBus getByBus(Bus bus);
}
