package com.eli.spring.boot.rest.jpa.hibernate.api.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.dto.CustomerDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRestControllerTests {

    private static final MockHttpServletRequestBuilder getCustomerCount = 
                                MockMvcRequestBuilders.get("/customers/count")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder getCustomers = 
                                MockMvcRequestBuilders.get("/customers")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder postCustomer = 
                                MockMvcRequestBuilders.post("/customers")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder deleteCustomers = 
                                MockMvcRequestBuilders.delete("/customers")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .accept(MediaType.APPLICATION_JSON);


    @Autowired
    private MockMvc mockMvc;

    private void deleteAll() throws Exception {
        mockMvc.perform(deleteCustomers)
                .andExpect(status().isOk())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                //.andExpect(no jsonPath("$").doesNotExist())
                ;
    }

    private void checkNumber(int number) throws Exception {
        mockMvc.perform(getCustomerCount)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.count").value(""+number))
                ;
    }

    @Test
    public void test0_deleteAll() throws Exception {
        deleteAll();
    }

    @Test
    public void test1_isEmpty() throws Exception {
        deleteAll();
        checkNumber(0);
    }

    @Test
    public void test2_addOneCustomer() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        System.err.println("\n\n\n\nbody: "+body+" :body\n\n\n\n\n\n\n");
        mockMvc.perform(postCustomer.content(body))
                .andExpect(status().isCreated())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                //.andExpect(jsonPath("$").isEmpty())
                ;
        checkNumber(1);
    }

    @Test
    public void test3_addOneCustomerVerifyInfo() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        System.err.println("\n\n\n\nbody: "+body+" :body\n\n\n\n\n\n\n");
        mockMvc.perform(postCustomer.content(body))
                .andExpect(status().isCreated())
                //.andExpect(content().contentType("application/json;charset=UTF-8"))
                //.andExpect(jsonPath("$").isEmpty())
                ;
        checkNumber(1);
        mockMvc.perform(getCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].fname").value("john"))
                .andExpect(jsonPath("$[0].lname").value("doe"))
                .andExpect(jsonPath("$[0].email").value("me@my.com"))
                ;
    }


    @Test
    public void test3_addAndGetOneCustomer() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        System.err.println("\n\n\n\nbody: "+body+" :body\n\n\n\n\n\n\n");
        mockMvc.perform(postCustomer.content(body))
                .andExpect(status().isCreated())
                ;
        checkNumber(1);
        MvcResult result = mockMvc.perform(getCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].fname").value("john"))
                .andExpect(jsonPath("$[0].lname").value("doe"))
                .andExpect(jsonPath("$[0].email").value("me@my.com"))
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        System.err.println("\n\n\n\n contentAsString:\n"+contentAsString+"\n\n\n\n");
        //Object object = new ObjectMapper().readValue(contentAsString,CustomerDTO.class);

    }




}
