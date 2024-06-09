package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    private User user;

    @Column(name = "base_salary")
    private double baseSalary;

    @Column(name = "overtime_hours")
    private double overtimeHours = 0.0;

    @Column(name = "holiday_work_hours")
    private double holidayWorkHours = 0.0;

    @Column(name = "night_shift_hours")
    private double nightShiftHours = 0.0;

    @Column(name = "sick_leave_hours")
    private double sickLeaveHours = 0.0;

    @Column(name = "overtime_pay_rate")
    private double overtimePayRate;

    @Column(name = "holiday_pay_rate")
    private double holidayPayRate;

    @Column(name = "night_shift_pay_rate")
    private double nightShiftPayRate;

    @Column(name = "sick_leave_type")
    private String sickLeaveType;

    @Column(name = "total_salary")
    private double totalSalary;

    public Salary() {
        // Initialize hour fields to zero
        this.overtimeHours = 0.0;
        this.holidayWorkHours = 0.0;
        this.nightShiftHours = 0.0;
        this.sickLeaveHours = 0.0;
    }

    public Salary(int id, User user, double baseSalary, double overtimeHours, double holidayWorkHours, double nightShiftHours, double sickLeaveHours, double overtimePayRate, double holidayPayRate, double nightShiftPayRate, String sickLeaveType, double totalSalary) {
        this.id = id;
        this.user = user;
        this.baseSalary = baseSalary;
        this.overtimeHours = overtimeHours;
        this.holidayWorkHours = holidayWorkHours;
        this.nightShiftHours = nightShiftHours;
        this.sickLeaveHours = sickLeaveHours;
        this.overtimePayRate = overtimePayRate;
        this.holidayPayRate = holidayPayRate;
        this.nightShiftPayRate = nightShiftPayRate;
        this.sickLeaveType = sickLeaveType;
        this.totalSalary = totalSalary;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getHolidayWorkHours() {
        return holidayWorkHours;
    }

    public void setHolidayWorkHours(double holidayWorkHours) {
        this.holidayWorkHours = holidayWorkHours;
    }

    public double getNightShiftHours() {
        return nightShiftHours;
    }

    public void setNightShiftHours(double nightShiftHours) {
        this.nightShiftHours = nightShiftHours;
    }

    public double getSickLeaveHours() {
        return sickLeaveHours;
    }

    public void setSickLeaveHours(double sickLeaveHours) {
        this.sickLeaveHours = sickLeaveHours;
    }

    public double getOvertimePayRate() {
        return overtimePayRate;
    }

    public void setOvertimePayRate(double overtimePayRate) {
        this.overtimePayRate = overtimePayRate;
    }

    public double getHolidayPayRate() {
        return holidayPayRate;
    }

    public void setHolidayPayRate(double holidayPayRate) {
        this.holidayPayRate = holidayPayRate;
    }

    public double getNightShiftPayRate() {
        return nightShiftPayRate;
    }

    public void setNightShiftPayRate(double nightShiftPayRate) {
        this.nightShiftPayRate = nightShiftPayRate;
    }

    public String getSickLeaveType() {
        return sickLeaveType;
    }

    public void setSickLeaveType(String sickLeaveType) {
        this.sickLeaveType = sickLeaveType;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
