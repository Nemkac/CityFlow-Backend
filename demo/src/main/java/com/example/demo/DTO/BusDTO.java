package com.example.demo.DTO;

import com.example.demo.Model.Route;

import java.util.List;

public class BusDTO {
    private String licencePlate;
    private String manufactureDate;
    private Integer seatingCapacity;
    private double currentMileage;
    private String chassisNumber;
    private String type;
    private Integer engineDisplacement;
    private Integer horsePower;
    private String transmission;
    private double batteryHealth;
    private Integer batteryCapacity;
    private List<Route> routes;

    public BusDTO() {
    }

//    public BusDTO(String licencePlate, List<Route> routes) {
//        this.licencePlate = licencePlate;
//        this.routes = routes;
//    }


    public BusDTO(String licencePlate, String manufactureDate, Integer seatingCapacity, double currentMileage, String chassisNumber, String type, Integer engineDisplacement, Integer horsePower, String transmission, double batteryHealth, Integer batteryCapacity, List<Route> routes) {
        this.licencePlate = licencePlate;
        this.manufactureDate = manufactureDate;
        this.seatingCapacity = seatingCapacity;
        this.currentMileage = currentMileage;
        this.chassisNumber = chassisNumber;
        this.type = type;
        this.engineDisplacement = engineDisplacement;
        this.horsePower = horsePower;
        this.transmission = transmission;
        this.batteryHealth = batteryHealth;
        this.batteryCapacity = batteryCapacity;
        this.routes = routes;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
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

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
