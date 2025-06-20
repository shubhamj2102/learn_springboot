package com.practice.springboot.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timeStamp;
    private String msg;
    private int statusCode;

    public ErrorResponse(LocalDateTime timeStamp, String msg, int statusCode) {
        this.timeStamp = timeStamp;
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
