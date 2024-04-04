package com.example.demo.DTO;

public class RegisterDTO {

    private String username;
    private String name;
    private String lastname;
    private String password;

    public RegisterDTO(String username, String name, String lastname, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
    }

    public RegisterDTO() {
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
}
