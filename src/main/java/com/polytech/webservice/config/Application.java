package com.polytech.webservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Laora on 02/04/2017.
 */

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}