package com.eli.spring.boot.rest.jpa.hibernate.api.app.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

@ControllerAdvice
public class ErrorAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public void handleErrorConstraintViolation(ConstraintViolationException e) {
        System.err.println("\n\n\nhandleErrorConstraintViolation(): " + e.getMessage()+ "\n\n\n");
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> messages = new ArrayList<>();
        violations.stream().forEach( action -> { messages.add(action.getMessage()); });
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public void handleErrorBadRequest(Exception e) {
        System.err.println("\n\n\nhandleErrorBadRequest(): " + e.getMessage()+ "\n\n\n");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public void handleErrorNotFound(Exception e) {
        System.err.println("\n\n\nhandleErrorNotFound(): " + e.getMessage()+ "\n\n\n");
    }

    @ResponseStatus
    @ExceptionHandler({Exception.class})
    public void handleErrorCatchAll(Exception e) {
        System.err.println("\n\n\nhandleErrorCatchAll(): " + e.getMessage()+ "\n\n\n" + e.getClass().getSimpleName()+"\n\n\n");
    }

}