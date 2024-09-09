package com.example.demo.Exceptions;

public class WidgetNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public WidgetNotFoundException(String message){
        super(message);
    }
}
