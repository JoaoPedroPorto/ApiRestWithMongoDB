package com.apirestwithmongodb.controller;

import com.apirestwithmongodb.constant.ApiMapping;
import com.apirestwithmongodb.constant.MessageEnum;
import com.apirestwithmongodb.exception.ApplicationException;
import com.apirestwithmongodb.exception.PreConditionFailedException;
import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.response.Response;
import com.apirestwithmongodb.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(ApiMapping.USER)
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "Lista todos os usuários ativos do sistema.")
    public ResponseEntity<Response<List<Users>>> listAllUsers() {
        Response<List<Users>> res = new Response<List<Users>>();
        res.setData(userService.listAllUsers());
        res.setMessage(MessageEnum.LIST_USER_SUCCESS.getMessage());
        return ResponseEntity.ok(res);
    }


    @PostMapping
    @ApiOperation(value = "Cria um nosso usuário no sistema.")
    /*@ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name", dataType = "String", required = true),
                    @ApiImplicitParam(
                            name = "mail", dataType = "String", required = true
                    )
            }
    )*/
    public ResponseEntity<Object> createUser(@RequestBody Users user)
            throws ApplicationException, PreConditionFailedException {
        userService.createUser(user);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", MessageEnum.CREATE_USER_SUCCESS.getMessage());
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza um usuário do sistema.")
    public ResponseEntity<Object> updateUser(@PathVariable("id") String id, @RequestBody Users user)
            throws ApplicationException, PreConditionFailedException {
        userService.updateUser(id, user);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", MessageEnum.UPDATE_USER_SUCCESS.getMessage());
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Exclui um usuário do sistema.")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id)
            throws ApplicationException, PreConditionFailedException {
        userService.deleteUser(id);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", MessageEnum.DELETE_USER_SUCCESS.getMessage());

        Users a = Users.builder().id("1").build();

        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

}
