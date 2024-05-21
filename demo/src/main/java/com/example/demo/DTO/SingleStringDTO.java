package com.example.demo.DTO;

public class SingleStringDTO {
    private String string;

    public SingleStringDTO(String string) {
        this.string = string;
    }

    public SingleStringDTO() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
