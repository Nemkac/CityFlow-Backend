package com.example.demo.DTO;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Route;

import java.util.List;

public class AddBusToRouteDTO {
    private Route selectedRoute;
    private List<Bus> selectedBuses;
    private boolean scaleDepartureTime;
    private boolean extendClosingTime;
    public AddBusToRouteDTO() {
    }
    public AddBusToRouteDTO(Route selectedRoute, List<Bus> selectedBuses, boolean scaleDepartureTime, boolean extendClosingTime) {
        this.selectedRoute = selectedRoute;
        this.selectedBuses = selectedBuses;
        this.scaleDepartureTime = scaleDepartureTime;
        this.extendClosingTime = extendClosingTime;
    }

    public Route getSelectedRoute() {
        return selectedRoute;
    }

    public void setSelectedRoute(Route selectedRoute) {
        this.selectedRoute = selectedRoute;
    }

    public List<Bus> getSelectedBuses() {
        return selectedBuses;
    }

    public void setSelectedBuses(List<Bus> selectedBuses) {
        this.selectedBuses = selectedBuses;
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
