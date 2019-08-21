package com.apirestwithmongodb.controller;

import com.apirestwithmongodb.constant.ApiMapping;
import com.apirestwithmongodb.constant.MessageEnum;
import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.exception.PreConditionFailedException;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.response.Response;
import com.apirestwithmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(ApiMapping.USER)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<Response<List<Users>>> listAllUsers() {
        Response<List<Users>> res = new Response<List<Users>>();
        res.setData(userService.listAllUsers());
        res.setMessage(MessageEnum.LIST_USER_SUCCESS.getMessage());
        return ResponseEntity.ok(res);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody Users user)
            throws ApplicationException, PreConditionFailedException {
        userService.createUser(user);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", MessageEnum.CREATE_USER_SUCCESS.getMessage());
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") String id, @RequestBody Users user)
            throws ApplicationException, PreConditionFailedException {
        userService.updateUser(id, user);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", MessageEnum.UPDATE_USER_SUCCESS.getMessage());
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id)
            throws ApplicationException, PreConditionFailedException {
        userService.deleteUser(id);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", MessageEnum.DELETE_USER_SUCCESS.getMessage());
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

}
