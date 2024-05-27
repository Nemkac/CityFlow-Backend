package com.example.demo.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Accountant")
public class Accountant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountantId;
    @OneToOne
    private User user;

    public Accountant() {
    }

    public Accountant(Integer accountantId, User user) {
        this.accountantId = accountantId;
        this.user = user;
    }

    public Integer getAccountantId() {
        return accountantId;
    }

    public void setAccountantId(Integer accountantId) {
        this.accountantId = accountantId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
