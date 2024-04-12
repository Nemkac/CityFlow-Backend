package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.BusServicing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusServicingRepository extends JpaRepository<BusServicing,Integer> {
    BusServicing save(BusServicing busServicing);
    BusServicing getBusServicingById(Integer id);

    //List<BusServicing> getAll();
    List<BusServicing> findAllByBusOrderByDateDesc(Bus bus);
}
