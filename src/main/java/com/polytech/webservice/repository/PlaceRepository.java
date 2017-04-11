package com.polytech.webservice.repository;

import com.polytech.webservice.dataBdd.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Cyprien on 05/04/2017.
 */
@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
    List<Place> findById(@Param("place_id") String place_id);

    @Query("{ state:'ACTIVE' }")
    List<Place>  findSortByNbComment(Sort sort);
}
