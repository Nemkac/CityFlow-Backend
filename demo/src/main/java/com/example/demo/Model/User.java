package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.util.List;

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
    private String email;
    @Column
    private String password;
    //'YYYY-MM-DD'
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Column(name = "telephone")
    private String phoneNumber;
    @Column
    private String roles;
    @Column
    private boolean employed;
    @Column
    private double eWallet;
    @Column(name = "profile_picture")
    private String profilePicture;

    public User(){}

    public User(int id, String username, String name, String lastname, String email, String password, LocalDate dateOfBirth, String phoneNumber, String roles, boolean employed) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.eWallet = 0;
        this.employed = employed;

    }

    public User(int id, String username, String name, String lastname, String email, String password, LocalDate dateOfBirth, String phoneNumber, String roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.eWallet = 0;
    }
    public User(String username, String name, String lastname, String email, String password, LocalDate dateOfBirth, String phoneNumber, String roles) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.eWallet = 0;
    }


    public User(String username, String name, String lastname, String email, String password, LocalDate dateOfBirth, String phoneNumber, String roles, boolean employed) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.employed = employed;
        this.eWallet = 0;
    }

    public User(int id, String username, String name, String lastname, String email, String password, LocalDate dateOfBirth, String phoneNumber, String roles, boolean employed, double eWallet, String profilePicture) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.employed = employed;
        this.eWallet = eWallet;
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public double geteWallet() {
        return eWallet;
    }

    public void seteWallet(double eWallet) {
        this.eWallet = eWallet;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }
}
