package com.eli.spring.boot.rest.jpa.hibernate.api.app;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Stream;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.dto.CustomerDTO;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRestControllerTests {

    private static final MockHttpServletRequestBuilder getCustomerCount = MockMvcRequestBuilders.get("/customers/count")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder getCustomers = MockMvcRequestBuilders.get("/customers")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder postCustomer = MockMvcRequestBuilders.post("/customers")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    private static final MockHttpServletRequestBuilder deleteCustomers = MockMvcRequestBuilders.delete("/customers")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

    @Autowired
    private MockMvc mockMvc;


    private ResultActions expectCustomersArray() throws Exception {
        return mockMvc.perform(getCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isArray());
    }

    private ResultActions expectCustomersArrayJustJohnDoe() throws Exception {
        return expectCustomersArray()
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].fname").value("john"))
                .andExpect(jsonPath("$[0].lname").value("doe"))
                .andExpect(jsonPath("$[0].email").value("me@my.com"))
                ;
    }

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

    private void createMany(int number) throws Exception {
        for (int i=0;i<number;i++) {
            Customer customer = new Customer("fname"+i,"lname"+i,"fname"+i+".lname"+i+"@email.com");
            String body = new ObjectMapper().writeValueAsString(customer);
            mockMvc.perform(postCustomer.content(body)) .andExpect(status().isCreated()) ;
        }
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
        mockMvc.perform(postCustomer.content(body)) .andExpect(status().isCreated()) ;
        checkNumber(1);
    }

    @Test
    public void test3_addOneCustomerVerifyInfo() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        mockMvc.perform(postCustomer.content(body)) .andExpect(status().isCreated()) ;
        checkNumber(1);
        expectCustomersArrayJustJohnDoe();
    }


    @Test
    public void test3_addAndGetOneCustomer() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        mockMvc.perform(postCustomer.content(body)) .andExpect(status().isCreated()) ;
        checkNumber(1);
        MvcResult result = expectCustomersArrayJustJohnDoe().andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        System.err.println("\n\n\n\n contentAsString:\n"+contentAsString+"\n\n\n\n");
        CustomerDTO[] customers = new ObjectMapper().readValue(contentAsString,CustomerDTO[].class);
        int id = customers[0].getId();
        MockHttpServletRequestBuilder getThisCustomer = MockMvcRequestBuilders.get("/customers/"+id)
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(getThisCustomer)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(""+id))
                .andExpect(jsonPath("$.fname").value("john"))
                .andExpect(jsonPath("$.lname").value("doe"))
                .andExpect(jsonPath("$.email").value("me@my.com"))
                ;
    }

    @Test
    public void test4_updateOneCustomer() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        mockMvc.perform(postCustomer.content(body))
                .andExpect(status().isCreated())
                ;
        checkNumber(1);
        MvcResult result = expectCustomersArrayJustJohnDoe().andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        System.err.println("\n\n\n\n contentAsString:\n"+contentAsString+"\n\n\n\n");
        CustomerDTO[] customers = new ObjectMapper().readValue(contentAsString,CustomerDTO[].class);
        customers[0].setFname("Johnathan");
        customers[0].setLname("Doerty");
        customers[0].setEmail("j.dowerty@email.com");
        String body2 = new ObjectMapper().writeValueAsString(customers[0]);
        int id = customers[0].getId();
        MockHttpServletRequestBuilder putThisCustomer = MockMvcRequestBuilders.put("/customers").content(body2)
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(putThisCustomer)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(""+id))
                .andExpect(jsonPath("$.fname").value("Johnathan"))
                .andExpect(jsonPath("$.lname").value("Doerty"))
                .andExpect(jsonPath("$.email").value("j.dowerty@email.com"))
                ;
    }


    @Test
    public void test5_addAndDeleteOneCustomer() throws Exception {
        deleteAll();
        checkNumber(0);
        Customer customer = new Customer("john","doe","me@my.com");
        String body = new ObjectMapper().writeValueAsString(customer);
        System.err.println("\n\n\n\nbody: "+body+" :body\n\n\n\n\n\n\n");
        mockMvc.perform(postCustomer.content(body))
                .andExpect(status().isCreated())
                ;
        checkNumber(1);
        MvcResult result = expectCustomersArrayJustJohnDoe().andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        CustomerDTO[] customers = new ObjectMapper().readValue(contentAsString,CustomerDTO[].class);
        int id = customers[0].getId();
        MockHttpServletRequestBuilder deleteThisCustomer = MockMvcRequestBuilders.delete("/customers/"+id)
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(deleteThisCustomer)
                .andExpect(status().isOk())
                ;
    }


    @Test
    public void test6_createMany() throws Exception {
        deleteAll();
        checkNumber(0);
        createMany(100);
        checkNumber(100);
    }


    @Test
    public void test7_getPagedCustomersAllInSinglePage() throws Exception {
        int numCustomers = 100;
        deleteAll();
        checkNumber(0);
        createMany(numCustomers);
        checkNumber(numCustomers);
        MockHttpServletRequestBuilder getPagedCustomers = MockMvcRequestBuilders.get("/customers?page=0&size="+numCustomers+"&dir=ASC&sort=id")
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        /*MvcResult result =*/ mockMvc.perform(getPagedCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalPages").exists())
                .andExpect(jsonPath("$.totalPages").value("1"))
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalElements").value(numCustomers));
                //.andReturn();
        /*
        String contentAsString = result.getResponse().getContentAsString();
        System.err.println("\n\n\n\ncontentAsString:");
        System.err.println(contentAsString);
        System.err.println("\n\n\n\n");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(contentAsString);
        String content = node.get("content").asText();
        System.err.println("\n\n\n\ncontent:");
        System.err.println(content);
        System.err.println("\n\n\n\n");
        List<Customer> customers = mapper.readValue(content, new TypeReference<List<Customer>>(){});
        assertTrue(customers.size() == 2);
        */
    }


    @Test
    public void test8_getPagedCustomersTwoPagesGetFirstPage() throws Exception {
        int numCustomers = 100;
        int sizeRequested = 50;
        deleteAll();
        checkNumber(0);
        createMany(numCustomers);
        checkNumber(numCustomers);
        MockHttpServletRequestBuilder getPagedCustomers = MockMvcRequestBuilders.get("/customers?page=0&size="+sizeRequested+"&dir=ASC&sort=id")
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(getPagedCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalPages").exists())
                .andExpect(jsonPath("$.totalPages").value("2"))
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalElements").value(numCustomers))
                .andExpect(jsonPath("$.numberOfElements").exists())
                .andExpect(jsonPath("$.numberOfElements").value(sizeRequested))
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.first").value("true"))
                .andExpect(jsonPath("$.last").exists())
                .andExpect(jsonPath("$.last").value("false"))
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.number").value("0"))
                ;
    }


    @Test
    public void test9_getPagedCustomersTwoPagesGetFirstPage() throws Exception {
        int numCustomers = 100;
        int sizeRequested = 50;
        int pageRequested = 1;
        deleteAll();
        checkNumber(0);
        createMany(numCustomers);
        checkNumber(numCustomers);
        MockHttpServletRequestBuilder getPagedCustomers = 
                        MockMvcRequestBuilders.get("/customers?page="+pageRequested+"&size="+sizeRequested+"&dir=ASC&sort=id")
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(getPagedCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalPages").exists())
                .andExpect(jsonPath("$.totalPages").value("2"))
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalElements").value(numCustomers))
                .andExpect(jsonPath("$.numberOfElements").exists())
                .andExpect(jsonPath("$.numberOfElements").value(sizeRequested))
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.first").value("false"))
                .andExpect(jsonPath("$.last").exists())
                .andExpect(jsonPath("$.last").value("true"))
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.number").value("1"))
                ;
    }


    @Test
    public void test10_getPagedCustomersThreePagesGetMiddlePage() throws Exception {
        int numCustomers = 99;
        int sizeRequested = 33;
        int pageRequested = 1;
        deleteAll();
        checkNumber(0);
        createMany(numCustomers);
        checkNumber(numCustomers);
        MockHttpServletRequestBuilder getPagedCustomers = 
                        MockMvcRequestBuilders.get("/customers?page="+pageRequested+"&size="+sizeRequested+"&dir=ASC&sort=id")
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(getPagedCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalPages").exists())
                .andExpect(jsonPath("$.totalPages").value("3"))
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalElements").value(numCustomers))
                .andExpect(jsonPath("$.numberOfElements").exists())
                .andExpect(jsonPath("$.numberOfElements").value(sizeRequested))
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.first").value("false"))
                .andExpect(jsonPath("$.last").exists())
                .andExpect(jsonPath("$.last").value("false"))
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.number").value("1"))
                ;
    }


    @Test
    public void test11_getPagedCustomersThreePagesGetFourthNonExistentPage() throws Exception {
        int numCustomers = 99;
        int sizeRequested = 33;
        int pageRequested = 3;
        deleteAll();
        checkNumber(0);
        createMany(numCustomers);
        checkNumber(numCustomers);
        MockHttpServletRequestBuilder getPagedCustomers = 
                        MockMvcRequestBuilders.get("/customers?page="+pageRequested+"&size="+sizeRequested+"&dir=ASC&sort=id")
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(getPagedCustomers)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalPages").exists())
                .andExpect(jsonPath("$.totalPages").value("3"))
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalElements").value(numCustomers))
                .andExpect(jsonPath("$.numberOfElements").exists())
                .andExpect(jsonPath("$.numberOfElements").value(0))
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.first").value("false"))
                .andExpect(jsonPath("$.last").exists())
                .andExpect(jsonPath("$.last").value("true"))
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.number").value("3"))
                ;
    }


}
