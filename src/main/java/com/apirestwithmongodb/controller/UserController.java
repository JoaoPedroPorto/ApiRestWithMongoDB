package com.apirestwithmongodb.controller;

import com.apirestwithmongodb.model.Users;
import com.apirestwithmongodb.response.Response;
import com.apirestwithmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<Response<List<Users>>> listAllUsers() {
        Response<List<Users>> res = new Response<List<Users>>();
        try {
            res.setMessage("Listagem de usuários retornada com sucesso...");
            res.setData(userService.listAllUsers());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setError("Erro interno do servidor...");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

}
