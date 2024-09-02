package com.example.demo.Exceptions;

public class LocationNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public LocationNotFoundException(String message){
        super(message);
    }
}
