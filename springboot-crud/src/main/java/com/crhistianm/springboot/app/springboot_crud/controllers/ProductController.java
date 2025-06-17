package com.crhistianm.springboot.app.springboot_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crhistianm.springboot.app.springboot_crud.entities.Product;
import com.crhistianm.springboot.app.springboot_crud.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> list(){
        return productService.findAll();
    }
    
}
