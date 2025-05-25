package com.crhistianm.springboot.calendar.interceptor.springboot_horario.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(){

       Map<String, Object> data = Collections.singletonMap("Title", "Bienvenidos al sistema de atencion!");
       return ResponseEntity.ok(data);
    }

}
