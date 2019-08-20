package com.apirestwithmongodb.dao;

import com.apirestwithmongodb.model.Applications;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationDAO extends MongoRepository<Applications, String> {

    Applications findOneByLogin(String login);

}
