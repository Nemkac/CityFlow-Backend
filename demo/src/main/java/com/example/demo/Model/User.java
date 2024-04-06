package com.example.demo.Model;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String password;
    private String roles;

    public User(String username, String name, String lastname, String password,String roles) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.roles = roles;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
