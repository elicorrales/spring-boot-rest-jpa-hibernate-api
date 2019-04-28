package com.eli.spring.boot.rest.jpa.hibernate.api.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRestControllerTests {

    private static final MockHttpServletRequestBuilder get = 
                                MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder post = 
                                MockMvcRequestBuilders.post("/customers").contentType(MediaType.APPLICATION_JSON)
                                                                         .accept(MediaType.APPLICATION_JSON);


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1_isEmpty() throws Exception {
        mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void test2_addOneCustomer() throws Exception {
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        System.err.println("\n\n\n\nbody: "+body+" :body\n\n\n\n\n\n\n");
        mockMvc.perform(post.content(body))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isEmpty());
    }



}
