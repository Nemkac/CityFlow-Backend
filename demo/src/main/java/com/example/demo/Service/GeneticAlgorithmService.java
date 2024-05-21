package com.example.demo.Service;

import com.example.demo.Model.GeneticAlgorithmOutput;
import com.example.demo.Repository.GeneticAlgorithmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneticAlgorithmService {

    @Autowired
    private ElectricBusService electricBusService;

    @Autowired
    private ChargingStationService chargingStationService;

    @Autowired
    private GeneticAlgorithmRepository geneticAlgorithmRepository;

    public GeneticAlgorithmOutput save(GeneticAlgorithmOutput geneticAlgorithmOutput) {
        return this.geneticAlgorithmRepository.save(geneticAlgorithmOutput);
    }



}
