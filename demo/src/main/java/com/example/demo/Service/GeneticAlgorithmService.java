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

    public GeneticAlgorithmOutput getByBus(ElectricBus bus){
        return this.geneticAlgorithmRepository.getByElBus(bus);
    }


    public List<GeneticAlgorithmOutput> transferOutputIntoChargingSchedule(Integer[] bestIndividuals){
        List<GeneticAlgorithmOutput> outputs = new ArrayList<GeneticAlgorithmOutput>();
        List<ChargingStation> stations = this.chargingStationService.getALl();
        List<ElectricBus> buses = this.electricBusService.getAll();
        for(int i = 0; i < bestIndividuals.length; i++) {
            int chargingCounter = -1;
            for(ChargingStation station : stations) {
                for(int j = 0; j < station.getNumOfPorts(); j++) {
                    chargingCounter++;
                    if (i == chargingCounter) {
                        if (station.getBusesCharging().size() < station.getNumOfPorts()) {
                            ElectricBus bus = this.electricBusService.getAll().get(i);
                            GeneticAlgorithmOutput newOutput = new GeneticAlgorithmOutput(bus, station, bestIndividuals[i],LocalDateTime.now(),LocalDateTime.now().plusMinutes(bestIndividuals[i]));
                            save(newOutput);
                            if (station.getBusesCharging() == null) {
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
                        }
                    }
                }
            }
        }
        int newChargerCounter = 0;
        List<ChargingStation> newStations = this.chargingStationService.getALl();
        for(ElectricBus bus : this.electricBusService.getAll()){
            boolean busFound = false;
            for(GeneticAlgorithmOutput output : this.getAll()){
                if(bus.equals(output.getBusId())){
                    busFound = true;
                }
            }
            if(!busFound){
                newChargerCounter++;
                for(ChargingStation station : newStations) {
                    if(this.chargingStationService.ifOccupied(station) != null){
                        GeneticAlgorithmOutput output = new GeneticAlgorithmOutput(bus,station,bestIndividuals[getAll().size()-1+newChargerCounter],this.chargingStationService.ifOccupied(station),this.chargingStationService.ifOccupied(station).plusMinutes(bestIndividuals[getAll().size()-1+newChargerCounter]));
                        save(output);
                        break;
                    }
                }
            }
        }
        return this.getAll();
    }


}
