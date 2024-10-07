package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column
    public String malfunctionDate;
    @Column
    public Integer seatingCapacity;
    @Column
    public double currentMileage;
    @Column
    public String chassisNumber;
    @Column
    public String licencePlate;

    @ManyToMany
    @JoinTable(
            name = "bus_route",
            joinColumns = @JoinColumn(name = "bus_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    @JsonIgnoreProperties(allowSetters = true, value = "buses")
    public List<Route> routes;

    public Bus() {
    }

    public Bus(String malfunctionDate, Integer seatingCapacity, double currentMileage, String chassisNumber, String licencePlate, List<Route> routes) {
        this.malfunctionDate = malfunctionDate;
        this.seatingCapacity = seatingCapacity;
        this.currentMileage = currentMileage;
        this.chassisNumber = chassisNumber;
        this.licencePlate = licencePlate;
        this.routes = routes;
    }

    public Integer getId() {
        return id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
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
}
