package com.example.demo.DTO;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Route;

import java.util.List;

public class AddRoutesToBusDTO {

    private Bus bus;
    private List<Route> routes;

    private boolean scaleDepartureTime;
    private boolean extendClosingTime;

    public AddRoutesToBusDTO() {
    }

    public AddRoutesToBusDTO(Bus bus, List<Route> routes, boolean scaleDepartureTime, boolean extendClosingTime) {
        this.bus = bus;
        this.routes = routes;
        this.scaleDepartureTime = scaleDepartureTime;
        this.extendClosingTime = extendClosingTime;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public boolean isScaleDepartureTime() {
        return scaleDepartureTime;
    }

    public void setScaleDepartureTime(boolean scaleDepartureTime) {
        this.scaleDepartureTime = scaleDepartureTime;
    }

    public boolean isExtendClosingTime() {
        return extendClosingTime;
    }

    public void setExtendClosingTime(boolean extendClosingTime) {
        this.extendClosingTime = extendClosingTime;
    }
}
