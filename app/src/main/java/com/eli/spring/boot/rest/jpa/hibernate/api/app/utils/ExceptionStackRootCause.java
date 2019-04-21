package com.eli.spring.boot.rest.jpa.hibernate.api.app.utils;

public class ExceptionStackRootCause {

    private ExceptionStackRootCause() {}

    public static Throwable getRootCause(Throwable t) {
        Throwable c = t.getCause();
        while (c != null) {
            t = c;
            c = c.getCause();
        }
        return t;
    }
}