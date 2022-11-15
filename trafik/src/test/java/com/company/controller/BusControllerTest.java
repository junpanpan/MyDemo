package com.company.controller;

import com.company.client.Client;
import com.company.entity.BusStop;
import com.company.entity.Response;
import com.company.entity.ResponseData;
import com.company.entity.Result;
import com.company.service.BusService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BusController.class, properties = {"server.address=localhost", "server.port=9999"})
public class BusControllerTest extends ControllerTestBase {

    @MockBean
    private Client client;

    @MockBean
    private BusService busService;

    @Test
    public void getHello() throws Exception {
        performGet("/bus")
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void test() throws Exception {
        createTestData();
        performGet("/bus/view").andExpect(status().isOk());

        verify(client, times(1)).sendRequestForBus();
        verify(client, times(1)).sendRequestForName();
        verify(busService, times(1)).addBusStop(new BusStop("1", "Stockholm, Solna"));
    }

    @DisplayName("ONLY FOR TEST")
    @Test
    public void test2() throws Exception {
        createTestData();
        performGet("/bus/test")
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(toJson(new String[][] { {"1", "Stockholm, Solna"} })));
    }

    private void createTestData() throws Exception {

        Response response = new Response();
        ResponseData responseData = new ResponseData();
        response.responseData = responseData;

        responseData.resultList = new ArrayList<>();
        Result result = new Result();
        result.stopPointNumber = "12";
        result.stopPointName = "Stockholm";

        result.lineNumber = "1";
        result.directionCode = "1";
        result.pointNumber = result.stopPointNumber;
        responseData.resultList.add(result);

        Result result2 = new Result();
        result2.stopPointNumber = "15";
        result2.stopPointName = "Solna";

        result2.lineNumber = "1";
        result2.directionCode = "1";
        result2.pointNumber = result2.stopPointNumber;
        responseData.resultList.add(result2);

        when(client.sendRequestForName()).thenReturn(response);
        when(client.sendRequestForBus()).thenReturn(response);
    }
}
