package com.example.demo.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="GeneticOutput")
public class GeneticAlgorithmOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genOutId;

    @OneToOne
    private ElectricBus elBus;

    @ManyToOne
    private ChargingStation chStation;

    @Column
    private Integer chargingTime;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    public GeneticAlgorithmOutput() {
    }

    public GeneticAlgorithmOutput(ElectricBus busId, ChargingStation chargingStationId, Integer chargingTime) {
        this.elBus = busId;
        this.chStation = chargingStationId;
        this.chargingTime = chargingTime;
    }

    public GeneticAlgorithmOutput(ElectricBus bus, ChargingStation chStation, Integer chargingTime, LocalDateTime startsAt, LocalDateTime endTime) {
        this.elBus = bus;
        this.chStation = chStation;
        this.chargingTime = chargingTime;
        this.startTime = startsAt;
        this.endTime = endTime;
    }



    public Integer getGenOutId() {
        return genOutId;
    }

    public ElectricBus getElBus() {
        return elBus;
    }

    public void setElBus(ElectricBus bus) {
        this.elBus = bus;
    }

    public ChargingStation getChargingStationId() {
        return chStation;
    }

    public void setChargingStationId(ChargingStation chStation) {
        this.chStation = chStation;
    }

    public Integer getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(Integer chargingTime) {
        this.chargingTime = chargingTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startsAt) {
        this.startTime = startsAt;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
