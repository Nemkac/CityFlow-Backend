package com.example.demo.Exceptions;

public class RouteNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public RouteNotFoundException(String message){
        super(message);
    }
}
