package com.example.demo.Service;

import com.example.demo.Model.ChargingStation;
import com.example.demo.Model.ElectricBus;
import com.example.demo.Model.GeneticAlgorithmOutput;
import com.example.demo.Repository.GeneticAlgorithmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<GeneticAlgorithmOutput> getAll(){
        return this.geneticAlgorithmRepository.findAll();
    }


    public List<GeneticAlgorithmOutput> transferOutputIntoChargingSchedule(Integer[] bestIndividuals){
        List<GeneticAlgorithmOutput> outputs = new ArrayList<GeneticAlgorithmOutput>();
        List<ChargingStation> stations = this.chargingStationService.getALl();
        List<ElectricBus> buses = this.electricBusService.getAll();

        for(Integer individual : bestIndividuals) {
            System.out.println(individual);
            for(ElectricBus bus : buses) {
                for(ChargingStation station : stations) {
                    if(station.getBusesCharging() == null || station.getBusesCharging().size() < station.getNumOfPorts()) {
                        GeneticAlgorithmOutput newOutput = new GeneticAlgorithmOutput(bus,station,individual, LocalDateTime.now(),LocalDateTime.now().plusMinutes(individual));
                        if(station.getChargerId() == null) {
                            List<ElectricBus> busesCharging = new ArrayList<ElectricBus>();
                            busesCharging.add(bus);
                            station.setBusesCharging(busesCharging);
                            this.chargingStationService.save(station);
                        } else {
                            List<ElectricBus> busesCharging = station.getBusesCharging();
                            busesCharging.add(bus);
                            station.setBusesCharging(busesCharging);
                            this.chargingStationService.save(station);
                        }
                        System.out.println("scigi sacuvam");
                        save(newOutput);
                        break;
                    }
                }
            }
        }
        return this.getAll();
    }


}
