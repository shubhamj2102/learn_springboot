package com.practice.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SEE_OTHER,reason = "Invalid user id is passed")
public class UserIdMissingCustomException extends RuntimeException{
    private String msg;
    private HttpStatus status;

    public UserIdMissingCustomException(String msg, HttpStatus status) {
        super(msg);
        this.status=status;
    }

    public String getMessage(){
        return super.getMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }
}
