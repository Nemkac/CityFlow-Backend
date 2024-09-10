package com.example.demo.DTO;

import com.example.demo.Model.Bus;
import jakarta.persistence.*;

@Entity
@Table(name="MalfunctionData")
public class MalfunctionDataDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String manufacturer;
    @Column
    private Integer inLastMonth;
    @Column
    private Integer inLastYear;
    @Column
    private Integer inLastFiveYears;
    @Column
    private Integer ever;

    public MalfunctionDataDTO() {}

    public MalfunctionDataDTO(String manufacturer, Integer inLastMonth, Integer inLastYear, Integer inLastFiveYears, Integer ever) {
        this.manufacturer = manufacturer;
        this.inLastMonth = inLastMonth;
        this.inLastYear = inLastYear;
        this.inLastFiveYears = inLastFiveYears;
        this.ever = ever;
    }

    public Integer getId(){
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getInLastMonth() {
        return inLastMonth;
    }

    public void setInLastMonth(Integer inLastMonth) {
        this.inLastMonth = inLastMonth;
    }

    public Integer getInLastYear() {
        return inLastYear;
    }

    public void setInLastYear(Integer inLastYear) {
        this.inLastYear = inLastYear;
    }

    public Integer getInLastFiveYears() {
        return inLastFiveYears;
    }

    public void setInLastFiveYears(Integer inLastFiveYears) {
        this.inLastFiveYears = inLastFiveYears;
    }

    public Integer getEver() {
        return ever;
    }

    public void setEver(Integer ever) {
        this.ever = ever;
    }
}
