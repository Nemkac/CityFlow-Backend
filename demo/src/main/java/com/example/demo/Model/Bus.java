package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Driver")
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
    private Long ChassisNumber;

    @Column
    private Long registrationNumber;

    @Column
    private Integer currentMileage;

    // dodaj za tip busa enum


    public Bus(){}

    public Bus(String manufacturer, String model, Integer modelYear, Integer seatingCapacity, Long chassisNumber, Long registrationNumber, Integer currentMileage) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.modelYear = modelYear;
        this.seatingCapacity = seatingCapacity;
        ChassisNumber = chassisNumber;
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

    public Long getChassisNumber() {
        return ChassisNumber;
    }

    public void setChassisNumber(Long chassisNumber) {
        ChassisNumber = chassisNumber;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
    }
}
