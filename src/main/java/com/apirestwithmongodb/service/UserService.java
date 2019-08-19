package com.apirestwithmongodb.service;

/*import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;*/

import com.apirestwithmongodb.model.Users;

import java.util.List;

public interface UserService {
    List<Users> listAllUsers();

   /* Flux<Users> listAllUsers();

    Mono<Users> createUser(Users user);*/

}
