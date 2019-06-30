package com.example.springapi.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspects {

    @Before("within(com.example.springapi.application.controller.UserController)")
    public void controllerStartLog(JoinPoint joinPoint) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("Start {}, args: {}", string, args);
    }

    @AfterReturning("within(com.example.springapi.application.controller.UserController)")
    public void controllerEndLog(JoinPoint joinPoint) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("End {}, args: {}", string, args);
    }

    @AfterThrowing(value = "within(com.example.springapi.application.controller.UserController)", throwing = "e")
    public void afterException(JoinPoint joinPoint, Exception e) {
        String string = joinPoint.toString();
        String args = Arrays.toString(joinPoint.getArgs());

        log.error("Error!, Exception: {}, {}, args = {}", e.getMessage(), string, args);
    }
}
