package com.company.client;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public final static String API_KEY = "81314581a6e34cb1b96f76b6639a4fbc";
    public final static String ENDPOINT_URI = "http://api.sl.se/api2/LineData.json?key=" + API_KEY + "&model=Line";

    public static String sendRequest() {

        final ClientConfig clientConfig = new DefaultClientConfig();
        final com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(clientConfig);
        final WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080").build());
        LOGGER.info(webResource.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));
        return "";
    }




}
