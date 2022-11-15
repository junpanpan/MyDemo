package com.company.controller;

import com.company.client.Client;
import com.company.client.ClientException;
import com.company.entity.BusStop;
import com.company.entity.Response;
import com.company.logic.Util;
import com.company.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bus")
public class BusController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusController.class);

    @Autowired
    private BusService busService;
    @Autowired
    private Client client;

    private String[][] cache;

    @GetMapping({"/", ""})
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping(path = "/view")
    public String get(Model model) throws ClientException {

        if (cache != null) {
            LOGGER.info("cached");
        } else {
            Response response1 = client.sendRequestForName();
            Response response2 = client.sendRequestForBus();

            cache = Util.getFirstMaxBusStops(response1, response2, 10);
            for (String[] s : cache) {
                busService.addBusStop(new BusStop(s[0], s[1]));
            }
        }

        model.addAttribute("busStops", busService.getBusStops());
        return "view";
    }

    @GetMapping(path = "/test", produces = {"application/json"})
    @ResponseBody
    public String[][] get2() throws ClientException {
        if (cache != null) {
            LOGGER.info("cached");
        } else {

            Response response1 = client.sendRequestForName();
            Response response2 = client.sendRequestForBus();

            cache = Util.getFirstMaxBusStops(response1, response2, 1);
        }

        return cache;
    }

}
