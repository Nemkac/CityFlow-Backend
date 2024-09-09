package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
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

    @ManyToMany
    @JoinTable(
            name = "route_stations",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    public List<Location> stations;

    @Enumerated(EnumType.STRING)
    @Column
    public RouteType type;

    @ManyToMany(mappedBy = "routes")
    @JsonIgnoreProperties("routes")
    public List<Bus> buses;

    @ManyToOne
    @JoinColumn(name = "endLocationID")
    public Location endPoint;

    @Column
    public String openingTime;

    @Column
    public String closingTime;

    @Column
    public Integer departureFromStartingStation;

    public Route() {}

    public Route(String name, Location startingPoint, List<Location> stations, Location endPoint, String openingTime, String closingTime, RouteType type) {
        this.name = name;
        this.startingPoint = startingPoint;
        this.stations = stations;
        this.endPoint = endPoint;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.buses = new ArrayList<Bus>();
        this.departureFromStartingStation = 0;
        this.type = type;
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

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
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

    public Integer getDepartureFromStartingStation() {
        return departureFromStartingStation;
    }

    public void setDepartureFromStartingStation(Integer departureFromStartingStation) {
        this.departureFromStartingStation = departureFromStartingStation;
    }
}
