package com.example.demo.Repository;

import com.example.demo.Model.ElectricBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricBusRepository extends JpaRepository<ElectricBus,Integer> {
    ElectricBus save(ElectricBus elBus);
    ElectricBus getByElBusId(Integer id);



}
