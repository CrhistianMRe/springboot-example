package com.crhistianm.springboot.app.springboot_crud.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crhistianm.springboot.app.springboot_crud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsInDatabaseValidation implements ConstraintValidator<IsInDatabase, String> {

    @Autowired
    private ProductService service;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        return !service.existsBySku(value);
    }

    
}
