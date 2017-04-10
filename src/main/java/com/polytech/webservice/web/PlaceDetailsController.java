package com.polytech.webservice.web;

import com.polytech.webservice.dataApi.PlaceDetailRequest;
import com.polytech.webservice.dataBdd.Place;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Laora on 10/04/2017.
 */

@RestController
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class PlaceDetailsController {

    @RequestMapping("/placeDetails/{idPlace}")
    public List<PlaceDetailRequest> placeSearch(@RequestParam(value = "idPlace") String idPlace) {
       return null ;
    }
}
