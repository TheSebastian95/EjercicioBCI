package com.challenge.sebastian.orellana.repository.impl;

import com.challenge.sebastian.orellana.entity.User;
import com.challenge.sebastian.orellana.exception.BackendException;
import com.challenge.sebastian.orellana.exception.ResourceAlreadyExistException;
import com.challenge.sebastian.orellana.exception.ResourceNotFoundException;
import com.challenge.sebastian.orellana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImp {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new BackendException("Failed to get users", e);
        }
    }

    public User getUserByEmail(String email) {
        User userEntity;
        try {
            userEntity = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new BackendException("Failed to get user: " + email, e);
        }

        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return userEntity;
    }

    public Optional<User> findByEmail(final String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User saveUser(User user) {
        if (findByEmail(user.getEmail()).isPresent()) throw new ResourceAlreadyExistException("User already exists");
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new BackendException("Error saved user: " + user.getEmail(), e);
        }
    }

    public User updateUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new BackendException("Error saved user: " + user.getEmail(), e);
        }
    }

    public void deleteUser(User userEntity) {
        try {
            userRepository.delete(userEntity);
        } catch (Exception e) {
            throw new BackendException("Failed to delete user: " + userEntity.getEmail(), e);
        }
    }
}
