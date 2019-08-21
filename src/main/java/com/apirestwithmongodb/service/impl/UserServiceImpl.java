package com.apirestwithmongodb.service.impl;

import com.apirestwithmongodb.constant.MessageEnum;
import com.apirestwithmongodb.constant.StatusEnum;
import com.apirestwithmongodb.dao.UserDAO;
import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.exception.PreConditionFailedException;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.service.UserService;
import com.apirestwithmongodb.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<Users> listAllUsers() {
        return userDAO
                .findAllByStatusNotOrderByNameAsc(StatusEnum.INACTIVE.getStatus())
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(Users user) throws ApplicationException, PreConditionFailedException {
        if ((user.getName() == null || user.getName().trim().isEmpty()) ||
                (user.getMail() == null || user.getMail().trim().isEmpty()))
            throw new PreConditionFailedException();
        Optional<Users> userDB = userDAO.findOneByMail(user.getMail().trim());
        if (userDB != null && userDB.isPresent() && !userDB.get().getStatus().equals(StatusEnum.INACTIVE))
            throw new ApplicationException(MessageEnum.EMAIL_EXIST_ERROR.getMessage());
        // EDITA USUARIO
        if (userDB != null && userDB.isPresent()) {
            userDB.get().setName(user.getName().trim());
            userDB.get().setPassword(ApplicationUtil.generatePassword());
            userDB.get().setStatus(StatusEnum.PENDING.getStatus());
            userDB.get().setEditedDate(new Date());
            Users usersaved = userDAO.save(userDB.get());
            if (usersaved == null) throw new ApplicationException(MessageEnum.CREATE_USER_ERROR.getMessage());
            return;
        }
        // CRIA USUARIO
        user.setStatus(StatusEnum.PENDING.getStatus());
        user.setPassword(ApplicationUtil.generatePassword());
        user.setName(user.getName().trim());
        user.setMail(user.getMail().trim());
        user.setCreatedDate(new Date());
        Users usersaved = userDAO.save(user);
        if (usersaved == null) throw new ApplicationException(MessageEnum.CREATE_USER_ERROR.getMessage());
    }

    @Override
    public void updateUser(String id, Users user) throws ApplicationException, PreConditionFailedException {
        if ((id == null || id.trim().isEmpty()) ||
                (user.getName() == null || user.getName().trim().isEmpty()))
            throw new PreConditionFailedException();
        Optional<Users> userDB = userDAO
                .findOneByIdAndStatusNot(id, StatusEnum.INACTIVE.getStatus());
        if (userDB == null || !userDB.isPresent())
            throw new ApplicationException(MessageEnum.USER_NOT_FOUND.getMessage());
        userDB.get().setName(user.getName().trim());
        userDB.get().setEditedDate(new Date());
        Users userSaved = userDAO.save(userDB.get());
        if (userSaved == null) throw new ApplicationException(MessageEnum.UPDATE_USER_ERROR.getMessage());
    }

    @Override
    public void deleteUser(String id) throws ApplicationException, PreConditionFailedException {
        if (id == null || id.trim().isEmpty())
            throw new PreConditionFailedException();
        Optional<Users> userDB = userDAO
                .findOneByIdAndStatusNot(id, StatusEnum.INACTIVE.getStatus());
        if (userDB == null || !userDB.isPresent())
            throw new ApplicationException(MessageEnum.USER_NOT_FOUND.getMessage());
        userDB.get().setStatus(StatusEnum.INACTIVE.getStatus());
        userDB.get().setEditedDate(new Date());
        Users userSaved = userDAO.save(userDB.get());
        if (userSaved == null) throw new ApplicationException(MessageEnum.DELETE_USER_ERROR.getMessage());
    }

}
