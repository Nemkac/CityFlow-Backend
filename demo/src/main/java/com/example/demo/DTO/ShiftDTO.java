package com.example.demo.DTO;
import java.time.LocalDateTime;

public class ShiftDTO {
    private int userId;
    private int routeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

    public ShiftDTO() {
    }

    public ShiftDTO(LocalDateTime startTime, LocalDateTime endTime, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public ShiftDTO(int userId, int routeId, LocalDateTime startTime, LocalDateTime endTime, String location) {
        this.userId = userId;
        this.routeId = routeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
}
