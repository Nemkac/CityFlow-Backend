package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int Id;

    public Administrator(){}


}
