package com.crhistianm.springboot.app.springboot_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crhistianm.springboot.app.springboot_crud.entities.User;
import com.crhistianm.springboot.app.springboot_crud.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.findAll();
    }
    
}
