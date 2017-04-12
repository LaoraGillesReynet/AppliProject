package com.polytech.webservice.web;

import com.polytech.webservice.dataApi.*;
import com.polytech.webservice.dataBdd.Place;
import com.polytech.webservice.repository.PlaceRepository;
import com.polytech.webservice.utils.DistanceCalculator;
import com.polytech.webservice.utils.InitializerArrayTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import com.polytech.webservice.dataBdd.*;

/**
 * Created by Laora on 02/04/2017.
 */

@RestController
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class GreetingController {

    @Autowired
    private PlaceRepository repository;

    @RequestMapping("/greeting")
    public List<Place> placesRequest(@RequestParam(value="latitude", defaultValue="0") double latitude, @RequestParam(value="longitude", defaultValue="0") double longitude) {
        repository.deleteAll();
        int compteurGoogleRequest = 0;
        //Requête API Météo
        String meteoString = "http://www.prevision-meteo.ch/services/json/lat=" + latitude + "lng=" + longitude;
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

        System.out.println("Nous sommes le " + day + " " + date + ", le temps est " + conditionMeteo + ", la température extérieure est de " + temperature + "°C.");
        System.out.println("Vous vous trouvez à " + altitude + "m d'altitude, le vent souffle à " + vitesseVent + "km/h direction " + dirVent);


        //Heure grâce à Calendar
        Calendar cal = Calendar.getInstance();
        int heure = cal.get(Calendar.HOUR_OF_DAY)+2;
        int minutes = cal.get(Calendar.MINUTE);

        //Requête API Google Places
        //Rayon et clé API
        int radius = 5000;
        String key = "AIzaSyDx6zH-3S7w8soSelA2sYFNk2n-GvnoTO8";

        //Initialisation des types
        String typeString = "";
        InitializerArrayTypes initializer = new InitializerArrayTypes();
        initializer.initialize();
        Iterator<String> iterator = initializer.getArrayTypes().iterator();
        while (iterator.hasNext()) {
            String current = iterator.next();
            typeString += '|' + current;
        }
        typeString = typeString.substring(1);
        System.out.println("Types: " + typeString);

        String next_page_token = "";
        String placeString = "";
        int index_token = 0;
        PlaceRequest placeRequest = new PlaceRequest();
        while (next_page_token != null) {
            //String de l'url avec paramètre
            if (index_token == 0)
                placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&types=" + typeString + "&radius=" + radius + "&key=" + key;
            else
                placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&types=" + typeString + "&radius=" + radius + "&key=" + key + "&pagetoken=" + next_page_token;
            compteurGoogleRequest += 1;
            RestTemplate restTemplate = new RestTemplate();
            placeRequest = restTemplate.getForObject(placeString, PlaceRequest.class);


            index_token+=1;


            boolean ok = false;
            for (int i = 0; i < placeRequest.getResults().size(); i++) {
                for (Place place2 : repository.findAll()) {
                    if (place2.getName().equals(placeRequest.getResults().get(i).getName()) && !ok) {
                        ok = true;
                    }
                }
                if (!ok) {
                    String id_place = placeRequest.getResults().get(i).getPlace_id();

                    //Requête Google Place Details
                    String detailString = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + id_place + "&key=" + key;
                    compteurGoogleRequest += 1;
                    RestTemplate restTemplateDetail = new RestTemplate();
                    PlaceDetailRequest placeDetailRequest = restTemplate.getForObject(detailString, PlaceDetailRequest.class);
                    System.out.println(placeDetailRequest);

                    //Création et initialisation de l'objet Place avant ajout en bdd
                    Place place = new Place();
                    place.setPlace_id(placeRequest.getResults().get(i).getPlace_id());
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
                    Photo photo = new Photo();
                    if (placeDetailRequest.getResult().getPhotos() != null) {
                        photo.setHeight(placeDetailRequest.getResult().getPhotos().get(0).getHeight());
                        photo.setWidth(placeDetailRequest.getResult().getPhotos().get(0).getWidth());
                        photo.setReference(placeDetailRequest.getResult().getPhotos().get(0).getPhoto_reference());
                        place.setPhotoRef(photo);
                    }

                    repository.save(place);
                }
                ok = false;
            }
            System.out.println(placeRequest.toString());
            next_page_token = placeRequest.getNext_page_token();
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

        System.out.println("nb requete api google :" + compteurGoogleRequest);

        // fetch all places

        boolean ok_types;
        double dist = 0;
        List<Place> resultList = new ArrayList<>();
        for (Place placebdd : repository.findAll())
        {
            ok_types = false;

            DistanceCalculator distanceCalculator = new DistanceCalculator();

            dist = distanceCalculator.distance(latitude, longitude, placebdd.getLatitude(), placebdd.getLongitude(), "K");
            System.out.println(latitude+" "+longitude+" "+placebdd.getLatitude()+" "+placebdd.getLongitude());
            System.out.println("DDDDDDDDDdistance : "+dist);
            InitializerArrayTypes initializer_result = new InitializerArrayTypes();
            initializer_result.initialize_result(heure, conditionMeteo, temperature, placebdd.getTypes());
            for (String string : initializer_result.getArrayTypes()){
                for ( String string2 : placebdd.getTypes()){
                    if (string2.equals(string) && !ok_types){
                        if(dist <= 7.0){
                            ok_types = true;
                        }
                    }
                }
            }
            if (ok_types)
            {
                resultList.add(placebdd);
                System.out.println(placebdd.getName());
            }
        }
        Comparator<Place> comparator = new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                DistanceCalculator distanceCalculator = new DistanceCalculator();
                double latitude1 = o1.getLatitude();
                double longitude1 = o1.getLongitude();
                double latitude2 = o2.getLatitude();
                double longitude2 = o2.getLongitude();
                double distance1 = distanceCalculator.distance(latitude, longitude, latitude1, longitude1, "K");
                double distance2 = distanceCalculator.distance(latitude, longitude, latitude2, longitude2, "K");

                if (distance1 <= distance2){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        };
        resultList.sort(comparator);
        return resultList;
    }
}