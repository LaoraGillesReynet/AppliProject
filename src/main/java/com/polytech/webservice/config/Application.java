package com.polytech.webservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Laora on 02/04/2017.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.polytech.webservice","com.polytech.webservice.web"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}