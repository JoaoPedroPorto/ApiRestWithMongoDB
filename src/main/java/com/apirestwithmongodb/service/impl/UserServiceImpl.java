package com.apirestwithmongodb.service.impl;

import com.apirestwithmongodb.constant.StatusEnum;
import com.apirestwithmongodb.dao.UserDAO;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<Users> listAllUsers() {
        List<Users> users = new ArrayList<>();
        userDAO
                .findAllByStatusNot(StatusEnum.INACTIVE.getStatus())
                .forEach(user -> {
                    user.setPassword(null);
                    users.add(user);
                });
        return users;
    }

    @Override
    public void createUser(Users user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) return;
        if (user.getMail() == null || user.getMail().trim().isEmpty()) return;
        user.setStatus(StatusEnum.PENDING.getStatus());
        user.setPassword("123456");
        userDAO.save(user);
    }

}
