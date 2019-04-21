package com.eli.spring.boot.rest.jpa.hibernate.api.app.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        super.configure(builder);
    }

}