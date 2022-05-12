package com.challenge.sebastian.orellana.repository.impl;

import com.challenge.sebastian.orellana.dto.PhoneDTO;
import com.challenge.sebastian.orellana.entity.Phone;
import com.challenge.sebastian.orellana.entity.User;
import com.challenge.sebastian.orellana.exception.BackendException;
import com.challenge.sebastian.orellana.repository.PhoneRepository;
import com.challenge.sebastian.orellana.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PhoneRepositoryImp {

    private final PhoneRepository phoneRepository;

    public List<Phone> findAllByUser(User user) {
        try {
            return phoneRepository.findAllByUser(user);
        } catch (Exception e) {
            throw new BackendException("Error getting the user's phone numbers: " + user.getEmail(), e);
        }
    }

    public List<PhoneDTO> getUsersPhones(User user) {
        List<Phone> phonesEntity = findAllByUser(user);
        List<PhoneDTO> phones = new ArrayList<>();
        phonesEntity.forEach(phone -> {
            phones.add(CommonUtil.mapperPhoneEntityToPhoneDto(phone));
        });
        return phones;
    }

    public void deleteUserPhone(User userEntity) {
        List<Phone> phonesEntity = findAllByUser(userEntity);
        try {
            phoneRepository.deleteAll(phonesEntity);
        } catch (Exception e) {
            throw new BackendException("Error deleting the user's phone list: " + userEntity.getEmail(), e);
        }
    }
}
