package com.example.demo.DTO;

import com.example.demo.Model.Bus;
import com.example.demo.Model.ElectricBus;
import com.example.demo.Model.ICEBus;

import java.util.List;

public class AllBusTypesDTO {
    private List<Bus> buses;
    private List<ICEBus> iceBuses;
    private List<ElectricBus> electricBuses;

    public AllBusTypesDTO() {
    }

    public AllBusTypesDTO(List<Bus> buses, List<ICEBus> iceBuses, List<ElectricBus> electricBuses) {
        this.buses = buses;
        this.iceBuses = iceBuses;
        this.electricBuses = electricBuses;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public List<ICEBus> getIceBuses() {
        return iceBuses;
    }

    public void setIceBuses(List<ICEBus> iceBuses) {
        this.iceBuses = iceBuses;
    }

    public List<ElectricBus> getElectricBuses() {
        return electricBuses;
    }

    public void setElectricBuses(List<ElectricBus> electricBuses) {
        this.electricBuses = electricBuses;
    }
}
