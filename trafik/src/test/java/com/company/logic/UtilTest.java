package com.company.logic;

import com.company.client.ClientException;
import com.company.entity.Response;
import com.company.entity.ResponseData;
import com.company.entity.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UtilTest {

    @Test
    public void errorTest() {

        Response response = new Response();
        Assertions.assertThrows(ClientException.class, () ->
                        Util.getFirstMaxBusStops(response, response, 1));
    }

    @DisplayName("set limit to 1")
    @Test
    public void test() throws ClientException {

        Response[] testData = getTestData();
        String[][] outcome = Util.getFirstMaxBusStops(testData[0], testData[1], 1);
        Assertions.assertEquals(1, outcome.length);

        Assertions.assertEquals("2", outcome[0][0]);
        Assertions.assertTrue(outcome[0][1].contains("Stockholm"));
        Assertions.assertTrue(outcome[0][1].contains("Solna"));
        Assertions.assertTrue(outcome[0][1].contains("Söder"));
    }

    @DisplayName("set limit to 2")
    @Test
    public void test2() throws ClientException {

        Response[] testData = getTestData();
        String[][]outcome = Util.getFirstMaxBusStops(testData[0], testData[1], 2);
        Assertions.assertEquals(2, outcome.length);

        Assertions.assertEquals("2", outcome[0][0]);
        Assertions.assertTrue(outcome[0][1].contains("Stockholm"));
        Assertions.assertTrue(outcome[0][1].contains("Solna"));
        Assertions.assertTrue(outcome[0][1].contains("Söder"));

        Assertions.assertEquals("1", outcome[1][0]);
        Assertions.assertTrue(outcome[1][1].equals("Stockholm"));
    }

    private Response[] getTestData() {

        Response response = new Response();
        ResponseData responseData = new ResponseData();
        response.responseData = responseData;

        responseData.resultList = new ArrayList<>();
        Result result = new Result();
        result.stopPointNumber = "12";
        result.stopPointName = "Stockholm";
        responseData.resultList.add(result);

        result = new Result();
        result.stopPointNumber = "13";
        result.stopPointName = "Solna";
        responseData.resultList.add(result);

        result = new Result();
        result.stopPointNumber = "14";
        result.stopPointName = "Söder";
        responseData.resultList.add(result);

        ////////////////////////

        Response response2 = new Response();
        ResponseData responseData2 = new ResponseData();
        response2.responseData = responseData2;

        responseData2.resultList = new ArrayList<>();
        result = new Result();
        result.lineNumber = "1";
        result.pointNumber = "12";
        result.directionCode = "1";
        responseData2.resultList.add(result);

        result = new Result();
        result.lineNumber = "2";
        result.pointNumber = "13";
        result.directionCode = "1";
        responseData2.resultList.add(result);

        result = new Result();
        result.lineNumber = "2";
        result.pointNumber = "12";
        result.directionCode = "1";
        responseData2.resultList.add(result);

        result = new Result();
        result.lineNumber = "2";
        result.pointNumber = "14";
        result.directionCode = "1";
        responseData2.resultList.add(result);

        return new Response[] {response, response2};
    }

    //More unit test.....
}
