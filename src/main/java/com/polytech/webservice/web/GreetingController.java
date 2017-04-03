package com.polytech.webservice.web;

import com.polytech.webservice.Greeting;
import com.polytech.webservice.data.MeteoRequest;
import com.polytech.webservice.data.PlacesRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by Laora on 02/04/2017.
 */

@RestController
public class GreetingController {

    private static final String template = "Lattitude: %s, Longitude:%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public MeteoRequest meteoRequest(@RequestParam(value="lattitude", defaultValue="0") double latitude, @RequestParam(value="longitude", defaultValue="0") double longitude) {
        //Requête API Météo
        String meteoString = "http://www.prevision-meteo.ch/services/json/lat="+latitude+"lng="+longitude;
        RestTemplate restTemplateMeteo = new RestTemplate();
        MeteoRequest meteoRequest = restTemplateMeteo.getForObject(meteoString, MeteoRequest.class);
        System.out.println(meteoRequest.toString());

        // Variables importantes
        int temperature = meteoRequest.getCurrent_condition().getTmp();
        String date = meteoRequest.getCurrent_condition().getDate();
        String day = meteoRequest.getFcst_day_0().getDay_short();
        double altitude = meteoRequest.getForecast_info().getElevation();
        String conditionMeteo = meteoRequest.getCurrent_condition().getCondition();
        int vitesseVent = meteoRequest.getCurrent_condition().getWnd_spd();
        String dirVent = meteoRequest.getCurrent_condition().getWnd_dir();

        System.out.println("Nous sommes le "+day+" "+date+", le temps est "+conditionMeteo+", la température extérieure est de "+temperature+"°C.");
        System.out.println("Vous vous trouvez à "+altitude+"m d'altitude, le vent souffle à "+vitesseVent+"km/h direction "+dirVent);



        //Requête API Google Places
        int radius =500;
        String types ="food";
        String key="AIzaSyDYuot7UKUyjnymjMt9M2KoyHSmqg_JTzM";
        String placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius="+radius+"&types="+types+"&key="+key;

        RestTemplate restTemplate = new RestTemplate();
        PlacesRequest placesRequest = restTemplate.getForObject(placeString, PlacesRequest.class);


        //With jsonObject
        /*try {
            URL url = new URL(placeString);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            String jsonString = "";
            while (null != (strTemp = br.readLine())) {
                jsonString = jsonString+"\n"+strTemp;
                System.out.println(strTemp);
            }
            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonObject placeObject = reader.readObject();
            reader.close();

            JsonArray results = placeObject.getJsonArray("results");
            for (JsonValue jsonValue : results) {
                JsonObject resultsObject = (JsonObject) jsonValue;
                System.out.println(resultsObject.getString("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */

        return meteoRequest;
    }
}