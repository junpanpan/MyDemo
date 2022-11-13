package com.company.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusStop busStop = (BusStop) o;
        return Objects.equals(busLine, busStop.busLine) &&
                Objects.equals(names, busStop.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busLine, names);
    }
}
