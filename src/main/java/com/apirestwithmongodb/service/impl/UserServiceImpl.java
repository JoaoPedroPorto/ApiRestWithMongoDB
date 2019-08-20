package com.apirestwithmongodb.service.impl;

import com.apirestwithmongodb.constant.MessageEnum;
import com.apirestwithmongodb.constant.StatusEnum;
import com.apirestwithmongodb.dao.UserDAO;
import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.service.UserService;
import com.apirestwithmongodb.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<Users> listAllUsers() throws ApplicationException {
        return userDAO
                .findAllByStatusNot(StatusEnum.INACTIVE.getStatus())
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(Users user) throws ApplicationException {
        if ((user.getName() == null || user.getName().trim().isEmpty()) ||
                (user.getMail() == null || user.getMail().trim().isEmpty()))
            throw new ApplicationException(MessageEnum.PARAMETER_EMPTY_OR_NULL.getMessage());
        Optional<Users> userDB = userDAO.findOneByMail(user.getMail().trim());
        if (userDB != null && userDB.isPresent() && !userDB.get().getStatus().equals(StatusEnum.INACTIVE))
            throw new ApplicationException(MessageEnum.EMAIL_EXIST_ERROR.getMessage());
        // EDITA USUARIO
        if (userDB != null && userDB.isPresent()) {
            userDB.get().setName(user.getName().trim());
            userDB.get().setPassword(ApplicationUtil.generatePassword());
            userDB.get().setStatus(StatusEnum.PENDING.getStatus());
            Users usersaved = userDAO.save(userDB.get());
            if (usersaved == null) throw new ApplicationException(MessageEnum.CREATE_USER_ERROR.getMessage());
            return;
        }
        // CRIA USUARIO
        user.setStatus(StatusEnum.PENDING.getStatus());
        user.setPassword(ApplicationUtil.generatePassword());
        Users usersaved = userDAO.save(user);
        if (usersaved == null) throw new ApplicationException(MessageEnum.CREATE_USER_ERROR.getMessage());
    }

    @Override
    public void updateUser(String id, Users user) throws ApplicationException {
        if ((id == null || id.trim().isEmpty()) ||
                (user.getName() == null || user.getName().trim().isEmpty()))
            throw new ApplicationException(MessageEnum.PARAMETER_EMPTY_OR_NULL.getMessage());
        Optional<Users> userDB = userDAO
                .findOneByIdAndStatusNot(id, StatusEnum.INACTIVE.getStatus());
        if (userDB == null || !userDB.isPresent())
            throw new ApplicationException(MessageEnum.USER_NOT_FOUND.getMessage());
        userDB.get().setName(user.getName().trim());
        Users userSaved = userDAO.save(userDB.get());
        if (userSaved == null) throw new ApplicationException(MessageEnum.UPDATE_USER_ERROR.getMessage());
    }

    @Override
    public void deleteUser(String id) throws  ApplicationException {
        if (id == null || id.trim().isEmpty())
            throw new ApplicationException(MessageEnum.PARAMETER_EMPTY_OR_NULL.getMessage());
        Optional<Users> userDB = userDAO
                .findOneByIdAndStatusNot(id, StatusEnum.INACTIVE.getStatus());
        if (userDB == null || !userDB.isPresent())
            throw new ApplicationException(MessageEnum.USER_NOT_FOUND.getMessage());
        userDB.get().setStatus(StatusEnum.INACTIVE.getStatus());
        Users userSaved = userDAO.save(userDB.get());
        if (userSaved == null) throw new ApplicationException(MessageEnum.DELETE_USER_ERROR.getMessage());
    }

}
