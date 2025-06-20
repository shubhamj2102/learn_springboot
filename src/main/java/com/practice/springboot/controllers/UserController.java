package com.practice.springboot.controllers;

import com.practice.springboot.entities.ErrorResponse;
import com.practice.springboot.entities.User;
import com.practice.springboot.exceptions.*;
import com.practice.springboot.exceptions.IllegalArgumentException;
import com.practice.springboot.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/data")
    public ResponseEntity<User> getData(@RequestParam String id){
        User user=userService.getUserData(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateData(@RequestParam String id){
        User user=userService.updateUserData(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/departments")
    public ResponseEntity<User> getDepartments(String id){
        throw new NoDepartmentsFoundException("No department is associated with userId",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/illegal")
    public ResponseEntity<User> getIllegalArgument(String id){
        throw new IllegalArgumentException("Arguments are illegal",LocalDateTime.now(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/global")
    public ResponseEntity<User> getDataGlobal(String id){
        throw new CustomExceptionForGlobal("global exception handled",LocalDateTime.now(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/response_status")
    public ResponseEntity<User> getDataResponseStatus(String id){
        throw new UserIdMissingCustomException("User Id is mising", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDataNotFoundException.class)
    public ResponseEntity<String> handleException(UserDataNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


//    @ExceptionHandler(UpdateNotAllowedException.class)
//    public ResponseEntity<ErrorResponse> handleUpdateException(UpdateNotAllowedException e){
//        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(LocalDateTime.now(),e.getMessage(),e.getStatus().value()));
//    }

    @ExceptionHandler({NoDepartmentsFoundException.class, UpdateNotAllowedException.class})
    public ResponseEntity<ErrorResponse> handleMultipleException(Exception e){
        HttpStatus status=HttpStatus.NOT_ACCEPTABLE;
        if(e instanceof NoDepartmentsFoundException){
            status=HttpStatus.NOT_FOUND;
        }
        var errorResponse=new ErrorResponse(LocalDateTime.now(),e.getMessage()+" (by multiple handler)",status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleException(HttpServletResponse response,IllegalArgumentException e) throws IOException {
        response.sendError(e.getStatus().value(),e.getMessage());
    }
}
