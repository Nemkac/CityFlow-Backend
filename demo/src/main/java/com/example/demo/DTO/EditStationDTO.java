package com.example.demo.DTO;

import com.example.demo.Model.Location;

public class EditStationDTO {
    private Location location;
    private String address;
    private double latitude;
    private double longitude;

    public EditStationDTO() {
    }

    public EditStationDTO(Location location, String address, double latitude, double longitude) {
        this.location = location;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
