package com.example.demo.Service;

import com.example.demo.Model.ChargingStation;
import com.example.demo.Model.GeneticAlgorithmOutput;
import com.example.demo.Repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChargingStationService {

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Autowired
    private GeneticAlgorithmService geneticAlgorithmService;

    public ChargingStation save(ChargingStation station){
        return this.chargingStationRepository.save(station);
    }

    public ChargingStation getById(Integer id){
        return this.chargingStationRepository.getByChargingStationId(id);
    }

    public List<ChargingStation> getALl(){
        return this.chargingStationRepository.findAll();
    }

    public LocalDateTime ifOccupied(ChargingStation station) {
        if(station.getBusesCharging().size() == station.getNumOfPorts()) {
            GeneticAlgorithmOutput output = this.geneticAlgorithmService.getByBus(station.getBusesCharging().get(station.getNumOfPorts()-1));
            return output.getEndTime();
        }
        return null;
    }

}
