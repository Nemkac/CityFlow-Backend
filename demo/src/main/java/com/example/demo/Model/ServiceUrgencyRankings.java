package com.example.demo.Model;


import jakarta.persistence.*;

@Entity
@Table(name="ServiceUrgencyRankings")
public class ServiceUrgencyRankings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Bus bus;

    @Column
    private Double score;

    @Column
    private Integer rank;

    @OneToOne
    private Bus fixedAfter;

    public ServiceUrgencyRankings() {}

    public ServiceUrgencyRankings(Bus bus, Double score, Integer rank) {
        this.bus = bus;
        this.score = score;
        this.rank = rank;
        this.fixedAfter = null;
    }

    public ServiceUrgencyRankings(Bus bus, Double score, Integer rank,Bus fixedAfter) {
        this.bus = bus;
        this.score = score;
        this.rank = rank;
        this.fixedAfter = fixedAfter;
    }

    public ServiceUrgencyRankings(Bus bus) {
        this.bus = bus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Bus getFixedAfter() {return fixedAfter;}
    public void setFixedAfter(Bus fixedAfter) {this.fixedAfter = fixedAfter;}



}
