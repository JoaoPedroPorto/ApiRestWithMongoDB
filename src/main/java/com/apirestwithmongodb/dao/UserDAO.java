package com.apirestwithmongodb.dao;

import com.apirestwithmongodb.model.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserDAO extends ReactiveMongoRepository<Users, String> {
}
