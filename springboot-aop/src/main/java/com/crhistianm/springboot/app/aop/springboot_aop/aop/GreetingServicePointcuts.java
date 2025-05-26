package com.crhistianm.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * GreetingServicePointcuts
 */

@Aspect
@Component
public class GreetingServicePointcuts {

    @Pointcut("execution(String com.crhistianm.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    private void greetingLoggerFooAspectPointCut(){}   
}
