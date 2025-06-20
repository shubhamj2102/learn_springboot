package com.practice.springboot.exception_handler;

import com.practice.springboot.entities.ErrorResponse;
import com.practice.springboot.exceptions.CustomExceptionForGlobal;
import com.practice.springboot.exceptions.UserIdMissingCustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptionForGlobal.class)
    public ResponseEntity<ErrorResponse> handleException(CustomExceptionForGlobal ex) {
        var errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), ex.getStatus().value());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

//    @ExceptionHandler(UserIdMissingCustomException.class)
//    @ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "reason outside the Exception handler")
//    public ResponseEntity<ErrorResponse> handleUserException(UserIdMissingCustomException ex) {
//        var errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), ex.getStatus().value());
//        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
//    }


    @ExceptionHandler(UserIdMissingCustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "reason outside the Exception handler")
    public void handleUserException(HttpServletResponse response,UserIdMissingCustomException ex) throws IOException {
       response.sendError(ex.getStatus().value(),ex.getMessage());

       /* this will give exception - 500-internal server error bcz
        response.sendError() first set the status and message in response and do commit.
        2nd ResponseStatus method will try to the same. Exception will occur in ExceptionableResolver class
         as we try to reset already commited status field */
    }


}
