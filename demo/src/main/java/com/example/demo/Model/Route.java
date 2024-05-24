package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column
    public String name;

    @ManyToOne
    @JoinColumn(name = "startLocationID")
    public Location startingPoint;

    @OneToMany
    public List<Location> stations;

    @ManyToOne
    @JoinColumn(name = "endLocationID")
    public Location endPoint;

    @Column
    public String openingTime;

    @Column
    public String closingTime;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shift> shifts;

    public Route() {}

    public Route(String name, Location startingPoint, List<Location> stations, Location endPoint, String openingTime, String closingTime) {
        this.name = name;
        this.startingPoint = startingPoint;
        this.stations = stations;
        this.endPoint = endPoint;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public Route(Integer id, String name, Location startingPoint, List<Location> stations, Location endPoint, String openingTime, String closingTime, List<Shift> shifts) {
        this.id = id;
        this.name = name;
        this.startingPoint = startingPoint;
        this.stations = stations;
        this.endPoint = endPoint;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.shifts = shifts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public List<Location> getStations() {
        return stations;
    }

    public void setStations(List<Location> stations) {
        this.stations = stations;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
