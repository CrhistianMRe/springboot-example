package com.crhistianm.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * GreetingAspect
 */

@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(String com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    //@Before("execution(String com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))")
    //@Before("execution(* com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))")
    public void loggerBefore(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();

        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(" Antes: " + method + " con los argumentos " + args);

    }

    
    @AfterReturning("execution(String com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();

        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(" Despues de un return: " + method + " con los argumentos " + args);

    }

    
    
}
