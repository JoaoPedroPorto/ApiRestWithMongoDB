package com.apirestwithmongodb.dao;

import com.apirestwithmongodb.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

public interface UserDAO extends MongoRepository<Users, String> {

    Stream<Users> findAllByStatusNot(String status);

}
