package com.example.demo.DTO;

import com.example.demo.Model.ChargingStation;
import com.example.demo.Model.ElectricBus;

import java.util.List;

public class GeneticAlgorithmInputDTO {
    private List<ElectricBus> buses;
    private List<ChargingStation> chargingStations;

    public GeneticAlgorithmInputDTO() {}

    public GeneticAlgorithmInputDTO(List<ElectricBus> buses, List<ChargingStation> chargingStations) {
        this.buses = buses;
        this.chargingStations = chargingStations;
    }

    public List<ElectricBus> getBuses() {
        return buses;
    }

    public void setBuses(List<ElectricBus> buses) {
        this.buses = buses;
    }

    public List<ChargingStation> getChargingStations() {
        return chargingStations;
    }

    public void setChargingStations(List<ChargingStation> chargingStations) {
        this.chargingStations = chargingStations;
    }
}
