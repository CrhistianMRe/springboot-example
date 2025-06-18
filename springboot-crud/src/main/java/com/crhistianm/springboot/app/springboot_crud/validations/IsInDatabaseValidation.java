package com.crhistianm.springboot.app.springboot_crud.validations;

import org.springframework.beans.factory.annotation.Autowired;

import com.crhistianm.springboot.app.springboot_crud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsInDatabaseValidation implements ConstraintValidator<IsInDatabase, String> {

    @Autowired
    private ProductService service;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !service.existsBySku(value);
    }

    
}
