package com.example.demo.Model;

import jakarta.persistence.*;


public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String address;
    @Column
    private Location location;

    public Station(String address, Location location) {
        this.address = address;
        this.location = location;
    }

    public Station(Location location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
