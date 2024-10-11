package com.example.demo.DTO;

import com.example.demo.Model.Bus;
import jakarta.persistence.Column;

public class EditBusDTO {
    private Bus bus;
    private String licencePlate;

    private String type;
    public String malfunctionDate;
    public Integer seatingCapacity;
    public double currentMileage;
    public String chassisNumber;
    private double batteryHealth;
    private Integer batteryCapacity;
    private Integer engineDisplacement;
    private String transmission;
    private Integer horsePower;

    public EditBusDTO() {
    }

//    public EditBusDTO(Bus bus, String licencePlate) {
//        this.bus = bus;
//        this.licencePlate = licencePlate;
//    }


    public EditBusDTO(Bus bus, String licencePlate, String type, String malfunctionDate, Integer seatingCapacity, double currentMileage, String chassisNumber, double batteryHealth, Integer batteryCapacity, Integer engineDisplacement, String transmission, Integer horsePower) {
        this.bus = bus;
        this.licencePlate = licencePlate;
        this.type = type;
        this.malfunctionDate = malfunctionDate;
        this.seatingCapacity = seatingCapacity;
        this.currentMileage = currentMileage;
        this.chassisNumber = chassisNumber;
        this.batteryHealth = batteryHealth;
        this.batteryCapacity = batteryCapacity;
        this.engineDisplacement = engineDisplacement;
        this.transmission = transmission;
        this.horsePower = horsePower;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getLincencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMalfunctionDate() {
        return malfunctionDate;
    }

    public void setMalfunctionDate(String malfunctionDate) {
        this.malfunctionDate = malfunctionDate;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public double getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(double currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public double getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(double batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }
}
