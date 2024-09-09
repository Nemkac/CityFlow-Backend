package com.example.demo.DTO;

import com.example.demo.Model.Bus;

public class EditBusDTO {
    private Bus bus;
    private String licencePlate;

    public EditBusDTO() {
    }

    public EditBusDTO(Bus bus, String licencePlate) {
        this.bus = bus;
        this.licencePlate = licencePlate;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getLincencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
}
