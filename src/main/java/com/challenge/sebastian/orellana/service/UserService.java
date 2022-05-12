package com.challenge.sebastian.orellana.service;

import com.challenge.sebastian.orellana.dto.UserDTO;
import com.challenge.sebastian.orellana.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    UserDTO getUser(String email);

    User createUser(UserDTO userDto);

    void deleteUser(String email);

    User updateUser(UserDTO userDto, String email);
}
