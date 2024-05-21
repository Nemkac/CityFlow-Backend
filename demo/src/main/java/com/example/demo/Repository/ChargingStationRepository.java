package com.example.demo.Repository;

import com.example.demo.Model.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation,Integer> {

    ChargingStation save(ChargingStation station);

    ChargingStation getByChargingStationId(Integer id);

}
