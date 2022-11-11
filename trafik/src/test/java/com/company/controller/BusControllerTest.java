package com.company.controller;

import com.company.client.Client;
import com.company.entity.Response;
import com.company.entity.ResponseData;
import com.company.entity.Result;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BusControllerTest extends ControllerTestBase {

    @MockBean
    private Client client;

    @Test
    public void getHello() throws Exception {
        performGet("/bus/")
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void test() throws Exception {
        performGet("/bus/view").andExpect(status().isOk());
    }

    @DisplayName("ONLY FOR TEST")
    @Test
    public void test2() throws Exception {
        performGet("/bus/test").andExpect(status().isOk());
    }

    @Disabled
    @Test
    public void get() throws Exception {

        Response response = new Response();
        ResponseData responseData = new ResponseData();
        response.responseData = responseData;

        responseData.resultList = new ArrayList<>();
        Result result = new Result();
        result.stopPointNumber = "12";
        result.stopPointName = "Stockholm";

        result.lineNumber = "1";
        result.pointNumber = result.stopPointNumber;
        responseData.resultList.add(result);

        when(client.sendRequestForName()).thenReturn(response);
        when(client.sendRequestForBus()).thenReturn(response);

        performGet("/test")
                .andExpect(status().isOk());
    }
}
