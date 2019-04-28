package com.eli.spring.boot.rest.jpa.hibernate.api.app.model;

public class MessageResponse {

    private String message;
    public MessageResponse(String message) { this.message = message; }
    public String getMessage() { return message; }
}