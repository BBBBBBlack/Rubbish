package com.example.tea_demo.myException;

public class WrongUrlException extends Exception{
    public WrongUrlException(String message){
        super(message);
    }
}
