package com.challenge.sebastian.orellana.service.impl;

import com.challenge.sebastian.orellana.dto.UserDTO;
import com.challenge.sebastian.orellana.entity.Phone;
import com.challenge.sebastian.orellana.entity.User;
import com.challenge.sebastian.orellana.exception.InvalidArgumentException;
import com.challenge.sebastian.orellana.repository.impl.PhoneRepositoryImp;
import com.challenge.sebastian.orellana.repository.impl.UserRepositoryImp;
import com.challenge.sebastian.orellana.security.UserDetailsImpl;
import com.challenge.sebastian.orellana.service.UserService;
import com.challenge.sebastian.orellana.util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private final ObjectMapper mapper;
    private final PhoneRepositoryImp phoneRepositoryImp;
    private final UserRepositoryImp userRepositoryImp;

    @Override
    public List<User> getAllUsers() {
        return userRepositoryImp.getAllUsers();
    }

    @Override
    public UserDTO getUser(String email) {
        if (!CommonUtil.validate(email)) throw new InvalidArgumentException("email " + email + " is invalid.");
        User userEntity = userRepositoryImp.getUserByEmail(email);
        return CommonUtil.mapperUserEntityToUserDto(userEntity, phoneRepositoryImp.getUsersPhones(userEntity));
    }

    @Override
    public User createUser(UserDTO userDTO) {
        CommonUtil.validatedRequest(userDTO);
        User user = mapper.convertValue(userDTO, User.class);
        return userRepositoryImp.saveUser(user);
    }

    @Override
    public void deleteUser(String mail) {
        CommonUtil.validate(mail);
        User user = userRepositoryImp.getUserByEmail(mail);
        phoneRepositoryImp.deleteUserPhone(user);
        userRepositoryImp.deleteUser(user);
    }

    @Override
    public User updateUser(UserDTO userDto, String email) {
        CommonUtil.validatedRequest(userDto);
        User user = userRepositoryImp.getUserByEmail(email);
        if (!userDto.getPassword().isEmpty())
            user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setModified(new Date());
        userDto.getPhones().forEach(d -> {
                    if (user.getPhones().stream().noneMatch(f -> f.getNumber() == d.getNumber())) {
                        user.getPhones().add(Phone.builder()
                                .number(d.getNumber())
                                .cityCode(d.getCityCode())
                                .countryCode(d.getCountryCode())
                                .user(user)
                                .build());
                    }
                }
        );
        return userRepositoryImp.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryImp.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
