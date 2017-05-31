package com.polytech.webservice.web;

import com.polytech.webservice.dataApi.*;
import com.polytech.webservice.dataBdd.Place;
import com.polytech.webservice.repository.PlaceRepository;
import com.polytech.webservice.repository.SearchRepository;
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
import sun.rmi.runtime.Log;

/*
 * Created by Laora on 02/04/2017.
 */

@RestController
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class GreetingController {

    @Autowired
    private PlaceRepository repository;

    @Autowired
    private SearchRepository repositoryS ;

    @RequestMapping("/greeting")
    public List<Place> placesRequest(@RequestParam(value="latitude") double latitude, @RequestParam(value="longitude") double longitude, @RequestParam(value="sort", defaultValue = "default", required = false) String sort,
                                     @RequestParam(value="search", defaultValue = "null", required = false) String search, @RequestParam(value="pref", defaultValue="null", required = false) String pref,
                                     @RequestParam(value="rayon", defaultValue="null", required = false) String rayon, @RequestParam(value="types", defaultValue="null", required = false) String types,
                                     @RequestParam(value="open", defaultValue="null", required = false) String openNow) {

        repository.deleteAll();
        repositoryS.deleteAll();
        int compteurGoogleRequest = 0;
        boolean startSearch = true;
        String typeString = "";
        String conditionMeteo = "";

        //Heure grâce à Calendar
        Calendar cal = Calendar.getInstance();
        int heure = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int jour = cal.get(Calendar.DAY_OF_MONTH);
        int mois = cal.get(Calendar.MONTH);
        int annee = cal.get(Calendar.YEAR);
        System.out.println(heure + "h " + minutes + "min ");

        // clé API Google
        String key = "AIzaSyCeb5X7OAdj5OoplVXXkKV0cAkuEp4FxEY";

        //Rayon
        int radius = 10000;

        InitializerArrayTypes initializer = new InitializerArrayTypes();

        if (search.equals("null") && rayon.equals("null")){
            if (pref.equals("null")){
                /*  PREPARATION RECHERCHE INITIALE */

                //Requête API Météo
                String meteoString = "http://www.prevision-meteo.ch/services/json/lat=" + latitude + "lng=" + longitude;
                RestTemplate restTemplateMeteo = new RestTemplate();
                MeteoRequest meteoRequest = restTemplateMeteo.getForObject(meteoString, MeteoRequest.class);

                // Variables importantes météo
                int temperature = meteoRequest.getCurrent_condition().getTmp();
                String date = meteoRequest.getCurrent_condition().getDate();
                String day = meteoRequest.getFcst_day_0().getDay_short();
                double altitude = meteoRequest.getForecast_info().getElevation();
                conditionMeteo = meteoRequest.getCurrent_condition().getCondition();
                int vitesseVent = meteoRequest.getCurrent_condition().getWnd_spd();
                String dirVent = meteoRequest.getCurrent_condition().getWnd_dir();

                System.out.println("Nous sommes le " + day + " " + date + ", le temps est " + conditionMeteo + ", la température extérieure est de " + temperature + "°C.");
                System.out.println("Vous vous trouvez à " + altitude + "m d'altitude, le vent souffle à " + vitesseVent + "km/h direction " + dirVent);

                initializer.initialize(heure, conditionMeteo, temperature);

            }
            else
            {
                /*  PREPARATION RECHERCHE PAR PREFERENCE (CATEGORIE) */
                initializer.initialize_pref(pref);
            }

            // INITIALE ET PREFERENCE COMMUN
            if (search.equals("null") && rayon.equals("null")) {
                if (pref.equals("null")) {

                } else
                    initializer.initialize_pref(pref);
                for (String current : initializer.getArrayTypes()) {
                    typeString += '|' + current;
                }
                if (!typeString.equals("")) {
                    typeString = typeString.substring(1);
                }
                System.out.println("Types: " + typeString);
            }

            List<Search> listSearch = repositoryS.findAll() ;
            for(Search search1 : listSearch){
                if (search1.getType_search().equals("initial")){
                    if ((search1.getHeure() == heure) && (search1.getMois() == mois) && (search1.getAnnee() == annee) && (jour <= search1.getJour() + 1) &&
                            (search1.getMeteo().equals(conditionMeteo)) && (latitude >= (search1.getLatitude() - 0.01) || latitude <= (search1.getLatitude() + 0.01))
                            && (longitude >= (search1.getLongitude() - 0.01) || longitude <= (search1.getLongitude() + 0.01))){

                        startSearch = false;
                    }
                }
                else if (search1.getType_search().equals("preference")){
                    if ((search1.getHeure() == heure) && (search1.getMois() == mois) && (search1.getAnnee() == annee) && (jour <= search1.getJour() + 1)
                            && search1.getTypes().equals(typeString)
                            && (latitude >= (search1.getLatitude() - 0.01) || latitude <= (search1.getLatitude() + 0.01))
                            && (longitude >= (search1.getLongitude() - 0.01) || longitude <= (search1.getLongitude() + 0.01))){
                        startSearch = false;
                    }
                }
            }
        }
        else{
            if (search.equals("null")){
                /*  PREPARATION RECHERCHE AVANCE */
            }
            else
            {
                /*  PREPARATION RECHERCHE AUTOCOMPLETE */
            }
        }

        ArrayList<Place> resultGoogleRequest = new ArrayList<>();
        Search recherche = new Search();
        if(startSearch) {
            String next_page_token = "";
            String placeString = "";
            int index_token = 0;
            PlaceRequest placeRequest = new PlaceRequest();
            while (next_page_token != null) {
                //String de l'url avec paramètre
                if (index_token == 0) {
                    if (search.equals("null")) {
                        if (pref.equals("null")){
                            if (rayon.equals("null")){
                                placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&types=" + typeString + "&radius=" + radius + "&key=" + key;
                                System.out.println(placeString);
                                recherche.setType_search("initial");
                                recherche.setLongitude(longitude);
                                recherche.setLatitude(latitude);
                                recherche.setTypes(typeString);
                                recherche.setRayon(radius);
                                recherche.setMeteo(conditionMeteo);
                                recherche.setHeure(heure);
                                recherche.setJour(jour);
                                recherche.setMois(mois);
                                recherche.setAnnee(annee);
                                recherche.setAutocompleteString("");
                                recherche.setOpenNow("");
                            }
                            else {
                                System.out.println("RECHERCHE AVANCE: je rentre ici !!!!");
                                recherche.setType_search("advance_search");
                                recherche.setLongitude(longitude);
                                recherche.setLatitude(latitude);
                                recherche.setRayon(Integer.parseInt(rayon));
                                recherche.setMeteo("");
                                recherche.setHeure(heure);
                                recherche.setJour(jour);
                                recherche.setMois(mois);
                                recherche.setAnnee(annee);
                                recherche.setAutocompleteString("");
                                recherche.setTypes("");
                                recherche.setOpenNow("");
                                placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=" + rayon;
                                if (!types.equals("null")) {
                                    String arrayTypes[] = types.split("-");
                                    String typesGoogle = "";
                                    for (int i = 0; i < arrayTypes.length; i++) {
                                        typesGoogle = typesGoogle + arrayTypes[i] + '|';
                                    }
                                    System.out.println(typesGoogle);
                                    recherche.setTypes(typesGoogle);
                                    placeString = placeString + "&types=" + typesGoogle;
                                }
                                if (openNow.equals("true")) {
                                    placeString = placeString + "&opennow";
                                    recherche.setOpenNow("");
                                }
                                placeString = placeString + "&key=" + key;
                                System.out.println(placeString);
                            }
                        }
                        else{
                            recherche.setType_search("preference");
                            recherche.setLongitude(longitude);
                            recherche.setLatitude(latitude);
                            recherche.setRayon(radius);
                            recherche.setMeteo("");
                            recherche.setHeure(heure);
                            recherche.setJour(jour);
                            recherche.setMois(mois);
                            recherche.setAnnee(annee);
                            recherche.setAutocompleteString("");
                            recherche.setTypes(typeString);
                            recherche.setOpenNow("");
                            placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&types=" + typeString + "&radius=" + radius + "&key=" + key;
                        }
                    }
                    else {
                        recherche.setType_search("autocomplete");
                        recherche.setLongitude(longitude);
                        recherche.setLatitude(latitude);
                        recherche.setRayon(0);
                        recherche.setMeteo("");
                        recherche.setHeure(heure);
                        recherche.setJour(jour);
                        recherche.setMois(mois);
                        recherche.setAnnee(annee);
                        recherche.setAutocompleteString(search);
                        recherche.setTypes("");
                        recherche.setOpenNow("");
                        placeString = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + search + "&key=" + key;
                        System.out.println(placeString);
                    }
                } else
                    placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&types=" + typeString + "&radius=" + radius + "&key=" + key + "&pagetoken=" + next_page_token;
                compteurGoogleRequest += 1;
                RestTemplate restTemplate = new RestTemplate();
                placeRequest = restTemplate.getForObject(placeString, PlaceRequest.class);


                index_token += 1;
                boolean ok = false;
                for (int i = 0; i < placeRequest.getResults().size(); i++) {
                    for (Place place2 : repository.findAll()) {
                        if (place2.getPlace_id().equals(placeRequest.getResults().get(i).getPlace_id()) && !ok) {
                            ok = true;
                            resultGoogleRequest.add(place2);
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

                            ArrayList<String> stringHoursWeek = new ArrayList<>();
                            if (placeDetailRequest.getResult().getOpening_hours().getWeekday_text() != null) {
                                for (String string : placeDetailRequest.getResult().getOpening_hours().getWeekday_text()) {
                                    stringHoursWeek.add(string);
                                }
                                horairesHebdo.setHorairesHebdo(stringHoursWeek);

                            }
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
                                    comment.setAspects(aspectArrayList);
                                }
                                commentArrayList.add(comment);
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
                        resultGoogleRequest.add(place);
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
        }
        repositoryS.save(recherche);
        System.out.println("nb requete api google :" + compteurGoogleRequest);
        // fetch all places

        List<Place> resultList = new ArrayList<>();
        if (search.equals("null")) {
            boolean ok_types;
            double dist = 0;
            for (Place placebdd : repository.findAll()) {
                ok_types = false;
                DistanceCalculator distanceCalculator = new DistanceCalculator();
                dist = distanceCalculator.distance(latitude, longitude, placebdd.getLatitude(), placebdd.getLongitude(), "K");
                for (String string : initializer.getArrayTypes()) {
                    for (String string2 : placebdd.getTypes()) {
                        if (string2.equals(string) && !ok_types) {
                            if (dist <= 7.0) {
                                ok_types = true;
                            }
                        }
                    }
                }
                if (ok_types) {
                    resultList.add(placebdd);
                }
            }
        }
        for (int i = 0; i< resultGoogleRequest.size(); i++)
            System.out.println(resultGoogleRequest.get(i).getName());
        for (int i = 0; i< resultList.size(); i++)
            System.out.println(resultList.get(i).getName());
        Comparator<Place> comparator;
        switch (sort) {
            case "default":
            case "dist":
            case "Distance":
                comparator = new Comparator<Place>() {
                    @Override
                    public int compare(Place o1, Place o2) {
                        DistanceCalculator distanceCalculator = new DistanceCalculator();
                        double latitude1 = o1.getLatitude();
                        double longitude1 = o1.getLongitude();
                        double latitude2 = o2.getLatitude();
                        double longitude2 = o2.getLongitude();
                        double distance1 = distanceCalculator.distance(latitude, longitude, latitude1, longitude1, "K");
                        double distance2 = distanceCalculator.distance(latitude, longitude, latitude2, longitude2, "K");

                        if (distance1 <= distance2) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                };
                if (search.equals("null")){
                    if (rayon.equals("null")){
                        resultList.sort(comparator);
                    }
                    else
                    {
                        resultGoogleRequest.sort(comparator);
                    }
                }
                else
                {
                    resultGoogleRequest.sort(comparator);
                }
                break;
            case "Importance":
                break;
        }
        if (search.equals("null")){
            if (rayon.equals("null"))
                return resultList;
            else
                resultGoogleRequest.toString();
                return resultGoogleRequest;
        }
        else
        {
            return resultGoogleRequest;
        }
    }


    @RequestMapping("/detail")
    public Place placeDetailRequest(@RequestParam(value="place_id") String place_id) {

        String key = "AIzaSyC9h5MYbC7YJB9DKdC4NUv4Pu91ip0UxS8";
        String detailString = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + place_id + "&key=" + key;
        RestTemplate restTemplate = new RestTemplate();
        PlaceDetailRequest placeDetailRequest = restTemplate.getForObject(detailString, PlaceDetailRequest.class);

        Place place = new Place();
        boolean ok = false;
        Place placebdd = new Place();
        for (Place place2 : repository.findAll()) {
            if (place2.getName().equals(placeDetailRequest.getResult().getName()) && !ok) {
                ok = true;
                placebdd = place2;
            }
        }
        if (!ok) {
            place.setPlace_id(placeDetailRequest.getResult().getPlace_id());
            place.setName(placeDetailRequest.getResult().getName());
            place.setAddress(placeDetailRequest.getResult().getFormatted_address());
            place.setLatitude(placeDetailRequest.getResult().getGeometry().getLocation().getLat());
            place.setLongitude(placeDetailRequest.getResult().getGeometry().getLocation().getLng());

            place.setTypes(placeDetailRequest.getResult().getTypes());
            place.setRating((int)placeDetailRequest.getResult().getRating());
            place.setPhoneNumber(placeDetailRequest.getResult().getFormatted_phone_number());
            place.setWebsite(placeDetailRequest.getResult().getWebsite());

            if (placeDetailRequest.getResult().getOpening_hours() != null) {
                ArrayList<HorairesHebdo.HorairesJour> horairesJours = new ArrayList<>();
                HorairesHebdo horairesHebdo = new HorairesHebdo();
                int index = 0;
                for (PlaceDetailValue.OpeningHours.HoursDay hoursDay : placeDetailRequest.getResult().getOpening_hours().getPeriods()) {
                    HorairesHebdo.HorairesJour horairesJour = new HorairesHebdo.HorairesJour();
                    if (placeDetailRequest.getResult().getOpening_hours().getPeriods().get(index).getClose() == null)
                        horairesJour.setFermeture(null);
                    else
                        horairesJour.setFermeture(placeDetailRequest.getResult().getOpening_hours().getPeriods().get(index).getClose().getTime());
                    horairesJour.setOuverture(placeDetailRequest.getResult().getOpening_hours().getPeriods().get(index).getOpen().getTime());
                    horairesJours.add(horairesJour);
                    index += 1;
                }
                horairesHebdo.setHoraires_jour(horairesJours);

                ArrayList<String> stringHoursWeek = new ArrayList<>();
                if (placeDetailRequest.getResult().getOpening_hours().getWeekday_text() != null) {
                    for (String string : placeDetailRequest.getResult().getOpening_hours().getWeekday_text()) {
                        stringHoursWeek.add(string);
                    }
                    horairesHebdo.setHorairesHebdo(stringHoursWeek);

                }
                place.setHoraires_hebdo(horairesHebdo);
            }


            if (placeDetailRequest.getResult().getReviews() != null) {
                ArrayList<Comment> commentArrayList = new ArrayList<>();
                for (int k = 0; k < placeDetailRequest.getResult().getReviews().size(); k++) {
                    Comment comment = new Comment();
                    comment.setLanguage(placeDetailRequest.getResult().getReviews().get(k).getLanguage());
                    comment.setAuteur(placeDetailRequest.getResult().getReviews().get(k).getAuthor_name());
                    comment.setCommentaire(placeDetailRequest.getResult().getReviews().get(k).getText());
                    comment.setRating(placeDetailRequest.getResult().getReviews().get(k).getRating());
                    comment.setTime(placeDetailRequest.getResult().getReviews().get(k).getTime());

                    if (placeDetailRequest.getResult().getReviews().get(k).getAspects() != null) {
                        ArrayList<Comment.Aspect> aspectArrayList = new ArrayList<>();
                        for (int l = 0; l < placeDetailRequest.getResult().getReviews().get(k).getAspects().size(); l++) {
                            Comment.Aspect aspect = new Comment.Aspect();
                            aspect.setType(placeDetailRequest.getResult().getReviews().get(k).getAspects().get(l).getTypes());
                            aspect.setRating(placeDetailRequest.getResult().getReviews().get(k).getAspects().get(l).getRating());
                            aspectArrayList.add(aspect);
                        }
                        comment.setAspects(aspectArrayList);
                    }
                    commentArrayList.add(comment);
                }
                place.setComment(commentArrayList);
            }
            Photo photo = new Photo();
            if (placeDetailRequest.getResult().getPhotos() != null) {
                photo.setHeight(placeDetailRequest.getResult().getPhotos().get(0).getHeight());
                photo.setReference(placeDetailRequest.getResult().getPhotos().get(0).getPhoto_reference());
                photo.setWidth(placeDetailRequest.getResult().getPhotos().get(0).getWidth());
                place.setPhotoRef(photo);
            }
            repository.save(place);
            return place;
        }
        else{
            return placebdd;
        }
    }
}