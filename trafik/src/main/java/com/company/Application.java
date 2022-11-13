package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }

    public static void main(String[] args) {

        LOGGER.info("Starting application.");
        SpringApplication.run(Application.class, args);
    }

//
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) {
//        LOGGER.info("uri " + Client.ENDPOINT_BUS_JOUR_URI);
//
//        return args -> {
//                Response response = restTemplate.getForObject(Client.ENDPOINT_BUS_JOUR_URI, Response.class);
//                LOGGER.info(response.toString());
//        };
//    }
}
