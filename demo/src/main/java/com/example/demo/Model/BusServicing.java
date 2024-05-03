package com.example.demo.Model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="BusService")
public class BusServicing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Bus bus;

    @Column
    private Integer mileage;

    @Column
    private LocalDate date;

    @Column
    private boolean ifEverythingFine;

    @Column
    private String commentary;

    public BusServicing() {}

    public BusServicing(Bus bus, Integer mileage, LocalDate date, boolean ifEverythingFine, String commentary) {
        this.bus = bus;
        this.mileage = mileage;
        this.date = date;
        this.ifEverythingFine = ifEverythingFine;
        this.commentary = commentary;
    }

    public BusServicing(Bus bus, LocalDate date){
        this.bus = bus;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public Integer getMileage() {
        return mileage;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isIfEverythingFine() {
        return ifEverythingFine;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setIfEverythingFine(boolean ifEverythingFine) {
        this.ifEverythingFine = ifEverythingFine;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
