package com.polytech.webservice.web;

import com.polytech.webservice.dataApi.PlaceDetailRequest;
import com.polytech.webservice.dataApi.PlaceDetailValue;
import com.polytech.webservice.dataApi.PlaceRequest;
import com.polytech.webservice.dataBdd.Comment;
import com.polytech.webservice.dataBdd.HorairesHebdo;
import com.polytech.webservice.dataBdd.Place;
import com.polytech.webservice.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


/**
 * Created by Laora on 10/04/2017.
 */

/*@RestController
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class ResearchController {

    @Autowired
    private PlaceRepository repository;

    @RequestMapping("/research")
    public PlaceRequest placeSearch(@RequestParam(value = "query") String name) {

        String key = "AIzaSyA6rPo1WPvwvDdW-53bptkeaaY2uL7Lgg8";

        PlaceRequest placeRequest = new PlaceRequest();

        //String de l'url avec paramètre
        String placeString = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + key;

        RestTemplate restTemplate = new RestTemplate();
        placeRequest = restTemplate.getForObject(placeString, PlaceRequest.class);

        boolean ok = false;
        for (int i = 0; i < placeRequest.getResults().size(); i++) {
            for (Place place : repository.findAll()) {
                if (place.getName().equals(placeRequest.getResults().get(i).getName()) && !ok) {
                    ok = true;
                }
            }
            if (!ok) {
                String id_place = placeRequest.getResults().get(i).getPlace_id();

                //Requête Google Place Details
                String detailString = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + id_place + "&key=" + key;
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
        return placeRequest;
    }
}
*/