package com.practice.springboot.services;

import com.practice.springboot.entities.User;
import com.practice.springboot.exceptions.UpdateNotAllowedException;
import com.practice.springboot.exceptions.UserDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserData(String id){
        throw new UserDataNotFoundException("user data is not present in DB");
    }

    public User updateUserData(String id){
        throw new UpdateNotAllowedException("Updation is not allowed due to maintaince", HttpStatus.NOT_ACCEPTABLE);
    }
}
