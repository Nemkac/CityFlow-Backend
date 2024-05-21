package com.example.demo.Model;


import jakarta.persistence.*;

@Entity
@Table(name="GeneticOutput")
public class GeneticAlgorithmOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genOutId;

    @Column
    private Integer busId;

    @Column
    private Integer chargingStationId;

    @Column
    private Integer chargingTime;



}
