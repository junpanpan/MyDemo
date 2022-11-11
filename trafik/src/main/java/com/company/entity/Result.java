package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("LineNumber")
    public String lineNumber;
    @JsonProperty("LineDesignation")
    public String lineDesignation;

    @Override
    public String toString() {
        return "Result {" +
                "LineNumber='" + lineNumber + '\'' +
                ", LineDesignation " + lineDesignation +
                '}';
    }
}
