package com.example.demo.DTO;

public class BalanceDTO {

    private String cardNumber;
    private String cardDate;
    private String cardHolder;
    private String cvs;
    private double payment;

    public BalanceDTO(String cardNumber, String cardDate, String cardHolder, String cvs,double Payment) {
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cardHolder = cardHolder;
        this.cvs = cvs;
        this.payment = Payment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }
}
