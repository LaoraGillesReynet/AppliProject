package com.polytech.webservice.repository;

import com.polytech.webservice.dataBdd.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cyprien on 05/04/2017.
 */
@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

}
