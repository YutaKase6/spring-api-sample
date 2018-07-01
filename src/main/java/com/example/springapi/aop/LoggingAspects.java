package com.example.springapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspects {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspects.class);

    @Before("within(com.example.springapi.application.controller.UserController)")
    public void controllerStartLog(JoinPoint joinPoint) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Start {}, args: {}", string, args);
    }

    @After("within(com.example.springapi.application.controller.UserController)")
    public void controllerEndLog(JoinPoint joinPoint) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("End {}, args: {}", string, args);
    }

    @AfterThrowing(value = "within(com.example.springapi.application.controller.UserController)", throwing = "e")
    public void afterException(JoinPoint joinPoint, Exception e) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.error("Error!, Exception: {}, {}, args = {}", e.getMessage(), string, args);
    }
}
