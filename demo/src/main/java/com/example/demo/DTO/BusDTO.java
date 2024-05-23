package com.example.demo.DTO;

import com.example.demo.Model.Route;

import java.util.List;

public class BusDTO {

    private String licencePlate;
    private List<Route> routes;

    public BusDTO() {
    }

    public BusDTO(String licencePlate, List<Route> routes) {
        this.licencePlate = licencePlate;
        this.routes = routes;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
