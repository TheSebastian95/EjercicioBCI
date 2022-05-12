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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(value = "RESTful API for user creation.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImp userServiceImp;

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> list() {
        return userServiceImp.getAllUsers();
    }


    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO retrieveByEmail(@PathVariable String email) {
        return userServiceImp.getUser(email);
    }


    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String email) {
        userServiceImp.deleteUser(email);
    }

    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User UpdateUser(@PathVariable String email, @RequestBody UserDTO userDto) {
        return userServiceImp.updateUser(userDto, email);
    }
}
