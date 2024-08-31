package com.example.demo.Exceptions;

public class DuplicateRouteException extends RuntimeException{
    private static final long serialVersionUID = 1;
    public DuplicateRouteException(String message) {
        super(message);
    }
}
