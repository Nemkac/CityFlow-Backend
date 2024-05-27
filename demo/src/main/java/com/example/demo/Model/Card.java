package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.function.DoubleToLongFunction;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column
    private String cardNumber;
    @Column
    private String cardDate;
    @Column
    private String cardHolder;
    @Column
    private String cvs;
    @Column
    private double balance;

    public Card() {
    }

    public Card(String cardNumber, String cardDate, String cardHolder, String cvs, double balance) {
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cardHolder = cardHolder;
        this.cvs = cvs;
        this.balance = balance;
    }

    public int getId() {

        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
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

