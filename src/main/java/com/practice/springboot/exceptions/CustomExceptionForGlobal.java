package com.practice.springboot.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomExceptionForGlobal extends RuntimeException{

    private String msg;
    private LocalDateTime timestamp;
    private HttpStatus status;

    public CustomExceptionForGlobal(String message, LocalDateTime timestamp, HttpStatus status) {
        this.msg = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
