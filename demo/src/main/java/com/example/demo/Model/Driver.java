package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer driverId;

    @OneToOne
    private User user;

    @OneToOne
    private Bus bus;

    public Driver() {
    }

    // ovo je samo za test, brise se posle
    public Driver (Integer driverId) {
        this.driverId = driverId;
        this.bus = null;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver(User user) {
        this.user = user;
        this.bus = null;
    }

    public Driver(User user, Bus bus){
        this.user = user;
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}
