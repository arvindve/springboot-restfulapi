package com.example.restfulwebservices.controllers;

import com.example.restfulwebservices.entities.User;
import com.example.restfulwebservices.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }

}
