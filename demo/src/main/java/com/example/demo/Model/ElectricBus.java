package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class ElectricBus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eBusId;

    @OneToOne
    private Bus bus;

    @Column(name = "batteryHealth")
    private Integer batteryHealth;

    @Column(name = "batteryCapacity")
    private Integer batteryCapacity;

    public ElectricBus(){}

    public ElectricBus(Bus bus, Integer batteryHealth, Integer batteryCapacity) {
        this.bus = bus;
        this.batteryHealth = batteryHealth;
        this.batteryCapacity = batteryCapacity;
    }

    public Integer geteBusId() {
        return eBusId;
    }

    public void seteBusId(Integer eBusId) {
        this.eBusId = eBusId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
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
}
