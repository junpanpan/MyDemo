package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("LineNumber")
    public String lineNumber;

    @JsonProperty("JourneyPatternPointNumber")
    public String pointNumber;

    @JsonProperty("DirectionCode")
    public String directionCode;

    @JsonProperty("StopPointNumber")
    public String stopPointNumber;

    @JsonProperty("StopPointName")
    public String stopPointName;


    @Override
    public String toString() {
        return "Result {" +
                "LineNumber='" + lineNumber + '\'' +
                ", JourneyPatternPointNumber " + pointNumber +
                ", StopPointNumber " + stopPointNumber +
                ", StopPointName " + stopPointName +
                '}';
    }
}
