package com.challenge.sebastian.orellana.repository;

import com.challenge.sebastian.orellana.entity.Phone;
import com.challenge.sebastian.orellana.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findAllByUser(User user);
}
