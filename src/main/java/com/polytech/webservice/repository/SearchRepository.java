package com.polytech.webservice.repository;

import com.polytech.webservice.dataBdd.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Laora on 01/05/2017.
 */
@Repository
public interface SearchRepository extends MongoRepository <Search, String> {
}
