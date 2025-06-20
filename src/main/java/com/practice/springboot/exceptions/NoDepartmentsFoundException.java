package com.practice.springboot.exceptions;

import org.springframework.http.HttpStatus;

public class NoDepartmentsFoundException extends RuntimeException{
    private String msg;
    private HttpStatus status;

    public NoDepartmentsFoundException(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    @Override
    public String getMessage(){
        return msg;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
