package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    Bus save(Bus bus);
    Bus getByBusId(Integer id);
}
