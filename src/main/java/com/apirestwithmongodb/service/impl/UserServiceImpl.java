package com.apirestwithmongodb.service.impl;


import com.apirestwithmongodb.dao.UserDAO;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public Flux<Users> listAllUsers() { return userDAO.findAll(); }

    @Override
    public Mono<Users> createUser(Users user) { return userDAO.save(user); }

}
