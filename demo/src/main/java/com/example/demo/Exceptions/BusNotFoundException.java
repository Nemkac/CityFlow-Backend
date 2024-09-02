package com.example.demo.Exceptions;

public class BusNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;
    public BusNotFoundException(String message) {
        super(message);
    }
}
