package com.example.demo.DTO;

public class DeleteBusFromRouteDTO {
    public Integer routeId;
    public Integer busId;

    public DeleteBusFromRouteDTO(){}

    public DeleteBusFromRouteDTO(Integer routeId, Integer busId) {
        this.routeId = routeId;
        this.busId = busId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
}
