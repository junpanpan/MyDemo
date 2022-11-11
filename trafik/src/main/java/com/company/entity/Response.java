package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("StatusCode")
    public String statusCode;
    @JsonProperty("ResponseData")
    public ResponseData responseData;


//    public ResponseData getResponseData() {
//        return responseData;
//    }
//
//    public void setResponseData(ResponseData responseData) {
//        this.responseData = responseData;
//    }

    @Override
    public String toString() {
        return "Response {" +
                "StatusCode='" + statusCode + '\'' +
                ", " + responseData.toString() +
                '}';
    }


}
