package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer busId;

    @Column
    private String manufacturer;

    @Column
    private String model;

    @Column
    private Integer modelYear;

    @Column
    private Integer seatingCapacity;

    @Column
    private Integer chassisNumber;

    @Column
    private String registrationNumber;

    @Column
    private Integer currentMileage;

    // dodaj za tip busa enum


    public Bus(){}

    public Bus(String manufacturer, String model, Integer modelYear, Integer seatingCapacity, Integer chassisNumber, String registrationNumber, Integer currentMileage) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.modelYear = modelYear;
        this.seatingCapacity = seatingCapacity;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.currentMileage = currentMileage;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(Integer chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
    }
}
