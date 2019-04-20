package com.eli.spring.boot.rest.jpa.hibernate.api.app.model;

public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) { this.message = message; }
    public String getMessge() { return message; }
}