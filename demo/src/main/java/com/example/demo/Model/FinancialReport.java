package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "financial_reports")
public class FinancialReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Incomes
    @Column
    private double dailyTicketSales;
    @Column
    private double monthlyTicketSales;
    @Column
    private double annualTicketSales;
    @Column
    private double sponsorshipRevenue;

    // Outcomes
    @Column
    private double totalSalariesPaid;
    @Column
    private double fuelCosts;
    @Column
    private double vehicleMaintenanceCosts;

    @Column
    @Temporal(TemporalType.DATE)
    private Date reportDate;

    @Column
    private double netProfit;

    public FinancialReport() {
    }

    public FinancialReport(int id, double dailyTicketSales, double monthlyTicketSales, double annualTicketSales, double sponsorshipRevenue, double totalSalariesPaid, double fuelCosts, double vehicleMaintenanceCosts, Date reportDate, double netProfit) {
        this.id = id;
        this.dailyTicketSales = dailyTicketSales;
        this.monthlyTicketSales = monthlyTicketSales;
        this.annualTicketSales = annualTicketSales;
        this.sponsorshipRevenue = sponsorshipRevenue;
        this.totalSalariesPaid = totalSalariesPaid;
        this.fuelCosts = fuelCosts;
        this.vehicleMaintenanceCosts = vehicleMaintenanceCosts;
        this.reportDate = reportDate;
        this.netProfit = netProfit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDailyTicketSales() {
        return dailyTicketSales;
    }

    public void setDailyTicketSales(double dailyTicketSales) {
        this.dailyTicketSales = dailyTicketSales;
    }

    public double getMonthlyTicketSales() {
        return monthlyTicketSales;
    }

    public void setMonthlyTicketSales(double monthlyTicketSales) {
        this.monthlyTicketSales = monthlyTicketSales;
    }

    public double getAnnualTicketSales() {
        return annualTicketSales;
    }

    public void setAnnualTicketSales(double annualTicketSales) {
        this.annualTicketSales = annualTicketSales;
    }

    public double getSponsorshipRevenue() {
        return sponsorshipRevenue;
    }

    public void setSponsorshipRevenue(double sponsorshipRevenue) {
        this.sponsorshipRevenue = sponsorshipRevenue;
    }

    public double getTotalSalariesPaid() {
        return totalSalariesPaid;
    }

    public void setTotalSalariesPaid(double totalSalariesPaid) {
        this.totalSalariesPaid = totalSalariesPaid;
    }

    public double getFuelCosts() {
        return fuelCosts;
    }

    public void setFuelCosts(double fuelCosts) {
        this.fuelCosts = fuelCosts;
    }

    public double getVehicleMaintenanceCosts() {
        return vehicleMaintenanceCosts;
    }

    public void setVehicleMaintenanceCosts(double vehicleMaintenanceCosts) {
        this.vehicleMaintenanceCosts = vehicleMaintenanceCosts;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }
}
