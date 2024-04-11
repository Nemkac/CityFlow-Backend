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
    private float score;

    @Column
    private Integer rank;

    public ServiceUrgencyRankings() {}

    public ServiceUrgencyRankings(Bus bus, float score, Integer rank) {
        this.bus = bus;
        this.score = score;
        this.rank = rank;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
    
}
