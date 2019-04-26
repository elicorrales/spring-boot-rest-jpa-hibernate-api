package com.eli.spring.boot.rest.jpa.hibernate.api.app.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.model.MessageResponse;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.utils.ExceptionStackRootCause;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleErrorDataIntegrityViolation(DataIntegrityViolationException e) {
        System.err.println("\n\n\nhandleErrorDataIntegrityViolation(): " + e.getMessage()+ "\n\n\n");
        Throwable t = ExceptionStackRootCause.getRootCause(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(t.getMessage()));
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleErrorConstraintViolation(ConstraintViolationException e) {
        System.err.println("\n\n\nhandleErrorConstraintViolation(): " + e.getMessage()+ "\n\n\n");
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (violations != null && violations.size() > 0) {
            List<String> messages = new ArrayList<>();
            violations.stream().forEach( action -> { messages.add(action.getMessage()); });
            return ResponseEntity.badRequest().body(messages);
        }
        return ResponseEntity.badRequest().build();
    }


    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleErrorBadRequest(Exception e) {
        System.err.println("\n\n\nhandleErrorBadRequest(): " + e.getMessage()+ "\n\n\n");
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
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