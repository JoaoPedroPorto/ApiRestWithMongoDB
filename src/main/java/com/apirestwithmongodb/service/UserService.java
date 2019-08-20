package com.apirestwithmongodb.service;

import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.model.Users;

import java.util.List;

public interface UserService {

    List<Users> listAllUsers() throws ApplicationException;

    void createUser(Users user) throws ApplicationException;

    void updateUser(String id, Users user) throws ApplicationException;

    void deleteUser(String id) throws  ApplicationException;
}
