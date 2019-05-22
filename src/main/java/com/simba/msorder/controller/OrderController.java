package com.simba.msorder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-05-18
 */
@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {
    private static final String LOCAL_SERVER_PORT = "local.server.port";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    @PostMapping
    public ResponseEntity<String> getOrder() {
        return ResponseEntity.ok("OrderController, Port: " +environment.getProperty(LOCAL_SERVER_PORT));
    }

    @GetMapping
    public ResponseEntity<String> getOrderWithProducts() {
        log.info("***** processing retrieve product from product-service *****");
        String product = restTemplate.getForObject("http://product-service/products", String.class);
        log.info("***** product retrieved = {} ", product);
        return ResponseEntity.ok("OrderController, Port: " +environment.getProperty(LOCAL_SERVER_PORT)+ " " +product);
    }
}
