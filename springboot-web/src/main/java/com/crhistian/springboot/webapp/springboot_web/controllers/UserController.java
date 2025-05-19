package com.crhistian.springboot.webapp.springboot_web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.crhistian.springboot.webapp.springboot_web.models.User;


@Controller
public class UserController {
    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Crhistian", "Mendez");
        user.setEmail("Crhistian@gmail.com");
        model.addAttribute("title", "Hello world Spring boot");
        model.addAttribute("user", user);
        
        

        return "details";
    }
    
    @GetMapping("/list")
    public String list(ModelMap model){

        //model.addAttribute("users", users);
        model.addAttribute("title", "List of users");

        return "list";

    }

    @ModelAttribute("users")
    public List<User> usersModel(){

        return Arrays.asList(    
        new User("Pepa", "Gonzales"),
        new User("Lalo", "Perez"),
        new User("Juanita", "Roe", "Juanita@gmail.com"),
        new User("Andres", "Doe", "Andres@gmail.com"));



    }

}
