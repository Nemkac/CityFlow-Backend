package com.example.demo.Model;


import jakarta.persistence.*;

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

    public GeneticAlgorithmOutput() {
    }

    public GeneticAlgorithmOutput(Integer busId, Integer chargingStationId, Integer chargingTime) {
        this.busId = busId;
        this.chargingStationId = chargingStationId;
        this.chargingTime = chargingTime;
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
}
