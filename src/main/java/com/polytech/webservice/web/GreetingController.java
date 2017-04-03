package com.polytech.webservice.web;

import com.polytech.webservice.business.InitializeArrayTypes;
import com.polytech.webservice.data.MeteoRequest;
import com.polytech.webservice.data.PlacesRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Laora on 02/04/2017.
 */

@RestController
public class GreetingController {

    private static final String template = "Lattitude: %s, Longitude:%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public PlacesRequest placesRequest(@RequestParam(value="latitude", defaultValue="0") double latitude, @RequestParam(value="longitude", defaultValue="0") double longitude) {
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


        //Heure grâce à Calendar
        Calendar cal = Calendar.getInstance();
        int heure = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        //Requête API Google Places
        //Initialisation Types
        InitializeArrayTypes initializer = new InitializeArrayTypes();
        initializer.InitializeArray(heure, minutes, conditionMeteo, temperature, vitesseVent);
        Iterator<String> iterator = initializer.getArray().iterator();
        String typesString ="";
        while(iterator.hasNext()){
            String current = iterator.next();
            typesString = typesString+"|"+current;
        }
        typesString = typesString.substring(1);

        System.out.println(typesString);
        //Rayon et clé API
        int radius =5000;
        String key="AIzaSyDYuot7UKUyjnymjMt9M2KoyHSmqg_JTzM";
        //String de l'url avec paramètre
        String placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius="+radius+"&types="+typesString+"&key="+key;

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

        return placesRequest;


    }
}