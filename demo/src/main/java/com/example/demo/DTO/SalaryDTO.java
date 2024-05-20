package com.example.demo.DTO;
public class SalaryDTO {

    private double baseSalary;
    private double overtimeHours;
    private double holidayWorkHours;
    private double nightShiftHours;
    private double sickLeaveHours;
    private double overtimePayRate;
    private double holidayPayRate;
    private double nightShiftPayRate;
    private String sickLeaveType;
    private double totalSalary;
    public SalaryDTO() {

    }

    public SalaryDTO(double baseSalary, double overtimeHours, double holidayWorkHours, double nightShiftHours, double sickLeaveHours, double overtimePayRate, double holidayPayRate, double nightShiftPayRate, String sickLeaveType, double totalSalary) {
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
