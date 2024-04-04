package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column
    public String username;

    public User(){}

    public User(String username) {
        this.username = username;
    }
}
