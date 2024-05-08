package com.example.demo.DTO;

public class KYCBalanceDTO {

    private String username;
    private double payment;

    public KYCBalanceDTO() {
    }

    public KYCBalanceDTO(String username, double payment) {
        this.username = username;
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
