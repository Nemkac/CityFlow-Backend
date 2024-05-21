package com.example.demo.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ChargingStation")
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chargingStationId;

    @Column
    private Integer outPutPower;

    @Column
    private Integer numOfPorts;

    @OneToMany
    private List<ElectricBus> busesCharging;

    public ChargingStation(){}


    public ChargingStation(Integer outPutPower, Integer numOfPorts, List<ElectricBus> busesCharging) {
        this.outPutPower = outPutPower;
        this.numOfPorts = numOfPorts;
        this.busesCharging = busesCharging;
    }

    public ChargingStation(List<ElectricBus> busesCharging) {
        this.outPutPower = 7200;
        this.numOfPorts = 6;
        this.busesCharging = busesCharging;
    }



    public Integer getChargerId() {
        return chargingStationId;
    }

    public Integer getOutPutPower() {
        return outPutPower;
    }

    public void setOutPutPower(Integer outPutPower) {
        this.outPutPower = outPutPower;
    }

    public Integer getNumOfPorts() {
        return numOfPorts;
    }

    public void setNumOfPorts(Integer numOfPorts) {
        this.numOfPorts = numOfPorts;
    }

    public List<ElectricBus> getBusesCharging() {
        return busesCharging;
    }

    public void setBusesCharging(List<ElectricBus> busesCharging) {
        this.busesCharging = busesCharging;
    }
}
