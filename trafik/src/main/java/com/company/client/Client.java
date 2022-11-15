package com.company.client;

import com.company.entity.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private final static String API_KEY = "81314581a6e34cb1b96f76b6639a4fbc";

    public final static String ENDPOINT_URI = "http://api.sl.se/api2/LineData.json?key=" + API_KEY + "&model=Line";

    public final static String ENDPOINT_BUS_JOUR_URI = "http://api.sl.se/api2/LineData.json?key=" + API_KEY
                                                    + "&DefaultTransportModeCode=BUS&model=Jour";

    public final static String ENDPOINT_STOPAREA_URI = "http://api.sl.se/api2/LineData.json?key=" + API_KEY
                                                    + "&model=StopArea";

    /**
     * To fetch mapping stopNr -> stopName
     * @return
     * @throws ClientException
     */
    public Response sendRequestForName() throws ClientException {

        final HttpPost httpPost = new HttpPost(ENDPOINT_STOPAREA_URI);
        return send(httpPost);
    }

    /**
     * To fetch mapping all bus lines
     */
    public Response sendRequestForBus() throws ClientException {

        final HttpPost httpPost = new HttpPost(ENDPOINT_BUS_JOUR_URI);
        return send(httpPost);
    }

    private Response send(HttpPost httpPost) throws ClientException {

        LOGGER.info("Sending request to trafiklab");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {

            //check response status
            LOGGER.info("Response status: " + httpResponse.getStatusLine());

            final int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (!isExpectedStatusCode(statusCode)) {
                throw new ClientException("Unexpected HTTP response status code: " + statusCode);
            }

            //extract content from response
            final HttpEntity entity = httpResponse.getEntity();
            final String content = EntityUtils.toString(entity, "UTF-16");
            if (content.isEmpty()) {
                throw new ClientException("Empty response content");
            }

            //finally convert JSON in response content to Response and return
            return jsonToResponse(content);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private boolean isExpectedStatusCode(int statusCode) {
        switch (statusCode) {
            case HttpStatus.SC_OK:
                return true;
            default:
                return false;
        }
    }

    private Response jsonToResponse(final String content) throws ClientException {
        try {
            return new ObjectMapper().readValue(content, Response.class);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }
}
