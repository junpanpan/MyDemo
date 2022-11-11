package com.company.entity;

public class BusStop {

    private String busLine;
    private String names;

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public BusStop() {}

    public BusStop(String busLine, String names) {
        this.busLine = busLine;
        this.names = names;
    }
}
