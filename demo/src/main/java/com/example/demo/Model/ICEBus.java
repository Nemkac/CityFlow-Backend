package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class ICEBus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iceBusId;

    @OneToOne
    private Bus bus;

    @Column(name = "displacement")
    private Integer engineDisplacement;

    @Column(name = "transmission")
    private Integer transmission;

    @Column(name = "horsePower")
    private Integer horsePower;

    public ICEBus(){}

    public ICEBus(Bus bus, Integer engineDisplacement, Integer transmission, Integer horsePower) {
        this.bus = bus;
        this.engineDisplacement = engineDisplacement;
        this.transmission = transmission;
        this.horsePower = horsePower;
    }

    public Integer getId() {
        return iceBusId;
    }

    public void setId(Integer id) {
        iceBusId = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public Integer getTransmission() {
        return transmission;
    }

    public void setTransmission(Integer transmission) {
        this.transmission = transmission;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }
}
