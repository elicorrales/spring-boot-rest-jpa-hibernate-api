package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.model.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("/")
public class MainRestController {
/*
    @GetMapping({"","/"})
    public ResponseEntity<?> main() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.err.println("\n\n\n user:"+auth.getName()+"\n\n\n");
        System.err.println("\n\n\n auths:"+auth.getAuthorities()+"\n\n\n");
        return new ResponseEntity<>(new MessageResponse(auth.getName()),HttpStatus.OK);
    }
*/

    @GetMapping("/error")
    public ResponseEntity<?> error() {
        return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }
}