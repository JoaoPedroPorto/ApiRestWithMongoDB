package com.apirestwithmongodb.controller;

import com.apirestwithmongodb.constant.ApiMapping;
import com.apirestwithmongodb.constant.MessageEnum;
import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.response.Response;
import com.apirestwithmongodb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMapping.USER)
public class UserController {

    @Autowired
    UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "")
    public ResponseEntity<Response<List<Users>>> listAllUsers() throws ApplicationException {
        Response<List<Users>> res = new Response<List<Users>>();
        try {
            res.setData(userService.listAllUsers());
            res.setMessage(MessageEnum.LIST_USER_SUCCESS.getMessage());
            Users user = new Users();
            return ResponseEntity.ok(res);
        } catch (ApplicationException e) {
            res.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(res);
        } catch (Exception e ) {
            LOGGER.error(e.getMessage(), e);
            res.setError(MessageEnum.INTERNAL_SERVER_ERROR.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Response<String>> createUser(@RequestBody Users user) throws ApplicationException {
        Response<String> res = new Response<String>();
        try {
            userService.createUser(user);
            res.setMessage(MessageEnum.CREATE_USER_SUCCESS.getMessage());
            return ResponseEntity.ok(res);
        } catch (ApplicationException e) {
            res.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            res.setError(MessageEnum.INTERNAL_SERVER_ERROR.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Response<String>> updateUser(@PathVariable("id") String id, @RequestBody Users user) throws ApplicationException {
        Response<String> res = new Response<String>();
        try {
            userService.updateUser(id, user);
            res.setMessage(MessageEnum.UPDATE_USER_SUCCESS.getMessage());
            return ResponseEntity.ok(res);
        } catch (ApplicationException e) {
            res.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            res.setError(MessageEnum.INTERNAL_SERVER_ERROR.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> deleteUser(@PathVariable("id") String id) throws ApplicationException {
        Response<String> res = new Response<String>();
        try {
            userService.deleteUser(id);
            res.setData(MessageEnum.DELETE_USER_SUCCESS.getMessage());
            return ResponseEntity.ok(res);
        } catch (ApplicationException e) {
            res.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            res.setError(MessageEnum.INTERNAL_SERVER_ERROR.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

}
