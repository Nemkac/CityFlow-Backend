package com.example.demo.Service;

import com.example.demo.Model.ChargingStation;
import com.example.demo.Repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingStationService {

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    public ChargingStation save(ChargingStation station){
        return this.chargingStationRepository.save(station);
    }

    public ChargingStation getById(Integer id){
        return this.chargingStationRepository.getByChargingStationId(id);
    }

    public List<ChargingStation> getALl(){
        return this.chargingStationRepository.findAll();
    }

}
