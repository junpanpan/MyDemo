package com.company.service;

import com.company.entity.BusStop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BusService {

    private List<BusStop> busStopList = new ArrayList<>();

    public Collection<BusStop> getBusStops() {
        return busStopList;
    }

    public void addBusStop(BusStop item) {
        busStopList.add(item);
    }
}
