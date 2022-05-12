package com.challenge.sebastian.orellana.controller;

import com.challenge.sebastian.orellana.dto.UserDTO;
import com.challenge.sebastian.orellana.entity.User;
import com.challenge.sebastian.orellana.service.impl.UserServiceImp;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/register")
@Api(value = "RESTful API for user creation.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RegisterController {

    private final UserServiceImp userServiceImp;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO userDTO) {
        return userServiceImp.createUser(userDTO);
    }
}
