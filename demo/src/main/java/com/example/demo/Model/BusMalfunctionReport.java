package com.example.demo.Model;


import jakarta.persistence.*;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDate;

@Entity
@Table(name="MalfunctionReport")
public class BusMalfunctionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer busMalfunctionReportId;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Bus bus;

    @Column
    private String summary;

    // videti da li ostaviti ovako nesto ili ubaciti enum za status zahteva kao na semi
    @Column
    private Boolean ifProcessed;

    @Column
    private LocalDate date;

    public BusMalfunctionReport(){}

    public BusMalfunctionReport(Driver driver, Bus bus, String summary, Boolean ifProcessed) {
        this.driver = driver;
        this.bus = bus;
        this.summary = summary;
        this.ifProcessed = ifProcessed;
        this.date = LocalDate.now();
    }

    public BusMalfunctionReport(Driver driver, Bus bus, String summary) {
        this.driver = driver;
        this.bus = bus;
        this.summary = summary;
        this.ifProcessed = false;
        this.date = LocalDate.now();
    }

    public BusMalfunctionReport(Driver driver, Bus bus, Boolean ifProcessed) {
        this.driver = driver;
        this.bus = bus;
        this.summary = "No summary";
        this.ifProcessed = ifProcessed;
        this.date = LocalDate.now();
    }

    public BusMalfunctionReport(Driver driver, Bus bus) {
        this.driver = driver;
        this.bus = bus;
        this.summary = "No summary";
        this.ifProcessed = false;
        this.date = LocalDate.now();
    }

    public Integer getBusMalfunctionReportId() {
        return busMalfunctionReportId;
    }

    public void setBusMalfunctionReportId(Integer busMalfunctionReportId) {
        this.busMalfunctionReportId = busMalfunctionReportId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getIfProcessed() {
        return ifProcessed;
    }

    public void setIfProcessed(Boolean ifProcessed) {
        this.ifProcessed = ifProcessed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
