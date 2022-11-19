package com.example.authenticationserver.controllers;

import com.example.authenticationserver.dto.RegistrationRequest;
import com.example.authenticationserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest registrationRequest)
    {
        userService.createUser(registrationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
