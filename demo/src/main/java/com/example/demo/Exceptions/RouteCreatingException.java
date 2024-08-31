package com.example.demo.Exceptions;

public class RouteCreatingException extends RuntimeException {
    private static final long serialVersionUID = 1;
    public RouteCreatingException(String message) {
        super(message);
    }
}
