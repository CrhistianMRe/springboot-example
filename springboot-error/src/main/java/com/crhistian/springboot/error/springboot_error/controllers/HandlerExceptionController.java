package com.crhistian.springboot.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.crhistian.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.crhistian.springboot.error.springboot_error.models.ErrorModel;


@RestControllerAdvice
public class HandlerExceptionController {

    
    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<ErrorModel> divisionByZero(Exception ex){
        ErrorModel error = new ErrorModel();
        error.setDate(new Date());
        error.setError("Error division por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        //return ResponseEntity.internalServerError().body(error);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }




    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> numberFormatException(Exception ex){

        Map<String, String> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "numero invalido o incorrecto, no tiene formato de digito!");
        error.put("message", ex.getMessage());
        error.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        return error;
    }

    @ExceptionHandler({NullPointerException.class, HttpMessageNotWritableException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception ex){

        Map<String, Object> error = new HashMap<>();

        error.put("Date", new Date());
        error.put("error ", "El usuario o rol no existe");
        error.put("Message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorModel> notFoundEx(NoHandlerFoundException e){
        ErrorModel error = new ErrorModel();
        error.setDate(new Date());    
        error.setError("Api rest no encontrado");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}
