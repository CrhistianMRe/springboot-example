package com.crhistianm.springboot.app.aop.springboot_aop.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * GreetingFooAspect
 */

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Before("execution(String com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    //@Before("execution(String com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))")
    //@Before("execution(* com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))")
    public void loggerBefore(JoinPoint joinPoint){


        String method = joinPoint.getSignature().getName();

        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(" Antes: " + method + " con los argumentos " + args);

    }   
}
