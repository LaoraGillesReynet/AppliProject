package com.polytech.webservice.web;

import com.polytech.webservice.dataApi.*;
import com.polytech.webservice.dataBdd.Place;
import com.polytech.webservice.repository.PlaceRepository;
import com.polytech.webservice.utils.InitializerArrayTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import com.polytech.webservice.dataBdd.*;

/**
 * Created by Laora on 02/04/2017.
 */

@RestController
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class GreetingController {

    private int compteurGoogleRequest;

    @Autowired
    private PlaceRepository repository;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public PlaceRequest placesRequest(@RequestParam(value="latitude", defaultValue="0") double latitude, @RequestParam(value="longitude", defaultValue="0") double longitude) {
        compteurGoogleRequest = 0;
        //Requête API Météo
        String meteoString = "http://www.prevision-meteo.ch/services/json/lat="+latitude+"lng="+longitude;
        RestTemplate restTemplateMeteo = new RestTemplate();
        MeteoRequest meteoRequest = restTemplateMeteo.getForObject(meteoString, MeteoRequest.class);
        System.out.println(meteoRequest.toString());

        // Variables importantes météo
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
        //Rayon et clé API
        int radius =5000;
        String key="AIzaSyDYuot7UKUyjnymjMt9M2KoyHSmqg_JTzM";

        //Initialisation des types
        String typeString="";
        InitializerArrayTypes initializer = new InitializerArrayTypes();
        initializer.initialize();
        Iterator<String> iterator = initializer.getArrayTypes().iterator();
        while (iterator.hasNext()){
            String current = iterator.next();
            typeString += '|'+current;
        }
        typeString = typeString.substring(1);

        //String de l'url avec paramètre
        String placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&types="+typeString+"&radius="+radius+"&key="+key+"&page_token=";

        compteurGoogleRequest += 1;

        RestTemplate restTemplate = new RestTemplate();
        PlaceRequest placeRequest = restTemplate.getForObject(placeString, PlaceRequest.class);
        System.out.println(placeRequest);

        String page_token = placeRequest.getNext_page_token();
        placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&types="+typeString+"&radius="+radius+"&key="+key+"&next_page_token="+page_token;
        compteurGoogleRequest += 1;
        restTemplate = new RestTemplate();
        placeRequest = restTemplate.getForObject(placeString, PlaceRequest.class);

        boolean ok = false;
        for(int i = 0; i < placeRequest.getResults().size(); i++) {
            for (Place place2 : repository.findAll()) {
                if (place2.getName().equals(placeRequest.getResults().get(i).getName()) && !ok) {
                    ok = true;
                }
            }
            if (!ok)
            {
                String id_place = placeRequest.getResults().get(i).getPlace_id();

                //Requête Google Place Details
                String detailString = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + id_place + "&key=" + key;
                compteurGoogleRequest += 1;
                RestTemplate restTemplateDetail = new RestTemplate();
                PlaceDetailRequest placeDetailRequest = restTemplate.getForObject(detailString, PlaceDetailRequest.class);
                System.out.println(placeDetailRequest);

                //Création et initialisation de l'objet Place avant ajout en bdd
                Place place = new Place();
                place.setName(placeRequest.getResults().get(i).getName());
                place.setAddress(placeDetailRequest.getResult().getFormatted_address());
                place.setLatitude(placeRequest.getResults().get(i).getGeometry().getLocation().getLat());
                place.setLongitude(placeRequest.getResults().get(i).getGeometry().getLocation().getLng());
                place.setTypes(placeRequest.getResults().get(i).getTypes());
                place.setRating(placeRequest.getResults().get(i).getRating());
                place.setPhoneNumber(placeDetailRequest.getResult().getFormatted_phone_number());
                place.setWebsite(placeDetailRequest.getResult().getWebsite());

                if (placeDetailRequest.getResult().getOpening_hours() != null) {
                    HorairesHebdo horairesHebdo = new HorairesHebdo();
                    ArrayList<HorairesHebdo.HorairesJour> horairesJours = new ArrayList<>();
                    int index = 0;
                    for (PlaceDetailValue.OpeningHours.HoursDay hoursDay : placeDetailRequest.getResult().getOpening_hours().getPeriods()) {
                        HorairesHebdo.HorairesJour horairesJour = new HorairesHebdo.HorairesJour();
                        horairesJour.setOuverture(placeDetailRequest.getResult().getOpening_hours().getPeriods().get(index).getOpen().getTime());
                        if (placeDetailRequest.getResult().getOpening_hours().getPeriods().get(index).getClose() == null)
                            horairesJour.setFermeture(null);
                        else
                            horairesJour.setFermeture(placeDetailRequest.getResult().getOpening_hours().getPeriods().get(index).getClose().getTime());
                        horairesJours.add(horairesJour);
                        index += 1;
                    }
                    horairesHebdo.setHoraires_jour(horairesJours);
                    place.setHoraires_hebdo(horairesHebdo);
                }

                if (placeDetailRequest.getResult().getReviews() != null) {
                    ArrayList<Comment> commentArrayList = new ArrayList<>();
                    for (int k = 0; k < placeDetailRequest.getResult().getReviews().size(); k++) {
                        Comment comment = new Comment();
                        comment.setAuteur(placeDetailRequest.getResult().getReviews().get(k).getAuthor_name());
                        comment.setCommentaire(placeDetailRequest.getResult().getReviews().get(k).getText());
                        comment.setLanguage(placeDetailRequest.getResult().getReviews().get(k).getLanguage());
                        comment.setRating(placeDetailRequest.getResult().getReviews().get(k).getRating());
                        comment.setTime(placeDetailRequest.getResult().getReviews().get(k).getTime());

                        if (placeDetailRequest.getResult().getReviews().get(k).getAspects() != null) {
                            ArrayList<Comment.Aspect> aspectArrayList = new ArrayList<>();
                            for (int l = 0; l < placeDetailRequest.getResult().getReviews().get(k).getAspects().size(); l++) {
                                Comment.Aspect aspect = new Comment.Aspect();
                                aspect.setRating(placeDetailRequest.getResult().getReviews().get(k).getAspects().get(l).getRating());
                                aspect.setType(placeDetailRequest.getResult().getReviews().get(k).getAspects().get(l).getTypes());
                                aspectArrayList.add(aspect);
                            }
                        }
                    }
                    place.setComment(commentArrayList);
                }
                repository.save(place);
            }
            ok = false;
        }
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
        // fetch all places
        System.out.println("Places found with findAll():");
        System.out.println("-------------------------------");
        for (Place placetest : repository.findAll()) {
            System.out.println(placetest.getName());
        }
        System.out.println(compteurGoogleRequest);

        repository.deleteAll();
        return placeRequest;
    }
}