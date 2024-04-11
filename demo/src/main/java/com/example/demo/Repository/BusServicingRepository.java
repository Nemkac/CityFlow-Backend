package com.example.demo.Repository;

import com.example.demo.Model.BusServicing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusServicingRepository extends JpaRepository<BusServicing,Integer> {
    BusServicing save(BusServicing busServicing);
    BusServicing getBusServicingById(Integer id);
}
