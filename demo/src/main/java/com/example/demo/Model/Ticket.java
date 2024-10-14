package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Integer Id;

    @Column
    private Integer busId;

    @Column
    private Integer routeId;

    @Column
    private LocalDateTime dateTimeNow;
    @Column
    private LocalDateTime expirationDateTime;
    @Column
    private boolean isUsed;

    public Ticket(){}

    public Ticket(Integer busId, Integer routeId, LocalDateTime dateTimeNow, LocalDateTime expirationDateTime) {
        this.busId = busId;
        this.routeId = routeId;
        this.dateTimeNow = dateTimeNow;
        this.expirationDateTime = expirationDateTime;
        this.isUsed = false;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public LocalDateTime getDateTimeNow() {
        return dateTimeNow;
    }

    public void setDateTimeNow(LocalDateTime dateTimeNow) {
        this.dateTimeNow = dateTimeNow;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
