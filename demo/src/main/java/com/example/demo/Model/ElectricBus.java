package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name="ElectricBus")
public class ElectricBus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer elBusId;

    @Column
    private Integer batteryHealth;

    @Column
    private Integer batteryCapacity;

    @Column
    private Integer energyConsumption;

    @Column
    private Integer routeLength;

    @Column
    private Integer batteryLevel;

    @OneToOne
    private Bus bus;

    public ElectricBus() {}

    public ElectricBus(Integer batteryHealth, Integer batteryCapacity, Integer energyConsumption, Integer routeLength, Integer batteryLevel, Bus bus) {
        this.batteryHealth = batteryHealth;
        this.batteryCapacity = batteryCapacity;
        this.energyConsumption = energyConsumption;
        this.routeLength = routeLength;
        this.batteryLevel = batteryLevel;
        this.bus = bus;
    }

    public ElectricBus(Bus bus) {
        this.batteryHealth = 100;
        this.batteryCapacity = 300;
        this.energyConsumption = 200;
        this.routeLength = 18;
        this.batteryLevel = 100;
        this.bus = bus;
    }

    public Integer getElBusId() {
        return elBusId;
    }

    public Integer getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(Integer batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public Integer getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(Integer routeLength) {
        this.routeLength = routeLength;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
