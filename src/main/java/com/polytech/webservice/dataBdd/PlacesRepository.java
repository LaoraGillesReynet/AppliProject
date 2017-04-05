package com.polytech.webservice.dataBdd;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Cyprien on 05/04/2017.
 */
public interface PlacesRepository extends MongoRepository<Place, String> {

}
