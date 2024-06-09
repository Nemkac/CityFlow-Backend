package com.example.demo.Model;

import com.example.demo.Model.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "terminations")
public class Termination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    private User user;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    @Column(name = "reason")
    private String reason;

    public Termination() {
    }

    public Termination(int id, User user, LocalDate terminationDate, String reason) {
        this.id = id;
        this.user = user;
        this.terminationDate = terminationDate;
        this.reason = reason;
    }

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

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
