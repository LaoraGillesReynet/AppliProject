package com.polytech.webservice.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * Created by Cyprien on 06/04/2017.
 */

/*@Configuration
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
public class MongoConfig extends AbstractMongoConfiguration{

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.polytech.webservice.dataBdd";
    }
}
*/

@Configuration
@EnableMongoRepositories(basePackages = "com.polytech.webservice.repository")
@ComponentScan(basePackages = {"com.polytech.webservice.repository","com.polytech.webservice.web", "com.polytech.webservice.dataBdd"})
public class MongoConfig {

    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://heroku_1bd6qs4g:m49l3467rhhc2qhjp8i10tsnut@ds157500.mlab.com:57500") ;
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "heroku_1bd6qs4g");
    }

}