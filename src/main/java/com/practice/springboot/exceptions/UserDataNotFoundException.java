package com.practice.springboot.exceptions;

public class UserDataNotFoundException extends RuntimeException{

    public UserDataNotFoundException(String msg){
        super(msg);
    }
}
