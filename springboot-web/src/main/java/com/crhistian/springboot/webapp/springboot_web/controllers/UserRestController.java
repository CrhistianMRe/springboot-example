package com.crhistian.springboot.webapp.springboot_web.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crhistian.springboot.webapp.springboot_web.models.User;
import com.crhistian.springboot.webapp.springboot_web.models.dto.UserDto;



@RestController
@RequestMapping("/api")
public class UserRestController {
    @GetMapping("/details")
    public UserDto details(){
       
        UserDto userDto = new UserDto();
        User user = new User("Crhistian", "Mendez"); 
        userDto.setUser(user);
        userDto.setTitle("Hello spring boot");

        
        

        return userDto;
    }

   

    @GetMapping("/list") 
    public List<User> list(){
        User user = new User("Crhistian", "Mendez");
        User user2 = new User("Pepe", "Perez");
        User user3 = new User("Carlos", "Garcia");


        List<User> users = Arrays.asList(user, user2, user3);
        //List<User> users = new ArrayList<>();
        //users.add(user);
        //users.add(user2);
        //users.add(user3);

        return users;


    }

    
    @GetMapping("/details-map")
    public Map<String, Object> detailsMap(){
        
        User user = new User("Crhistian", "Mendez"); 
        Map<String, Object> body = new HashMap<>();

        
        body.put("title", "Hello world Spring boot");
        body.put("user", user);
        

        return body;
    }
    


    




}
