package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.ICEBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICEBusRepository extends JpaRepository<ICEBus, Integer> {
    ICEBus save(ICEBus bus);

    List<ICEBus> findAll();
    boolean existsByBus(Bus bus);

    ICEBus getByBus(Bus bus);
}
