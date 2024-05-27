package com.example.demo.DTO;

import com.example.demo.Model.Location;

import java.util.List;

public class RouteDTO {
    public String routeName;
    public Location startingPoint;
    public Location endingPoint;
    public List<Location> stations;
    public String openingTime;
    public String closingTime;

    public RouteDTO(String routeName, Location startingPoint, Location endingPoint, List<Location> stations, String openingTime, String closingTime) {
        this.routeName = routeName;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.stations = stations;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public RouteDTO() {
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Location getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Location endingPoint) {
        this.endingPoint = endingPoint;
    }

    public List<Location> getStations() {
        return stations;
    }

    public void setStations(List<Location> stations) {
        this.stations = stations;
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
}
