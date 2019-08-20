package com.apirestwithmongodb.service;

import com.apirestwithmongodb.model.Users;

import java.util.List;

public interface UserService {

    List<Users> listAllUsers();

    void createUser(Users user);
}
