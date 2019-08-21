package com.apirestwithmongodb.service;

import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.exception.PreConditionFailedException;
import com.apirestwithmongodb.model.Users;

import java.util.List;

public interface UserService {

    List<Users> listAllUsers();

    void createUser(Users user) throws ApplicationException, PreConditionFailedException;

    void updateUser(String id, Users user) throws ApplicationException, PreConditionFailedException;

    void deleteUser(String id) throws ApplicationException, PreConditionFailedException;
}
