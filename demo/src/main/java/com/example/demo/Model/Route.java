package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @ManyToOne
    @JoinColumn(name = "startLocationID")
    public Location startingPoint;

    @OneToMany
    public List<Location> stations;

    @ManyToOne
    @JoinColumn(name = "endLocationID")
    public Location endPoint;

    public Route() {}

    public Route(Location startingPoint, List<Location> stations, Location endPoint) {
        this.startingPoint = startingPoint;
        this.stations = stations;
        this.endPoint = endPoint;
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
}
