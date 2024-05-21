package com.example.demo.Model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="GeneticOutput")
public class GeneticAlgorithmOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genOutId;

    @Column
    private Integer busId;

    @Column
    private Integer chargingStationId;

    @Column
    private Integer chargingTime;

    @Column
    private String startTime;

    @Column
    private String endTime;

    public GeneticAlgorithmOutput() {
    }

    public GeneticAlgorithmOutput(Integer busId, Integer chargingStationId, Integer chargingTime) {
        this.busId = busId;
        this.chargingStationId = chargingStationId;
        this.chargingTime = chargingTime;
    }

    public GeneticAlgorithmOutput(Integer busId, Integer chargingStationId, Integer chargingTime, String startsAt, String endTime) {
        this.busId = busId;
        this.chargingStationId = chargingStationId;
        this.chargingTime = chargingTime;
        this.startTime = startsAt;
        this.endTime = endTime;
    }



    public Integer getGenOutId() {
        return genOutId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getChargingStationId() {
        return chargingStationId;
    }

    public void setChargingStationId(Integer chargingStationId) {
        this.chargingStationId = chargingStationId;
    }

    public Integer getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(Integer chargingTime) {
        this.chargingTime = chargingTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startsAt) {
        this.startTime = startsAt;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
