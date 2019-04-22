package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

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
}