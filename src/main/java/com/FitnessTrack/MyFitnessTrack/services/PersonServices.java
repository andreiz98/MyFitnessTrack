package com.FitnessTrack.MyFitnessTrack.services;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PersonServices {

    List<PersonDto> findAllUsers();
    void deleteUserById(Long id);
    Person addUser(Person user);
    Optional<Person> findByUsername(String username);
    Person updatePerson(Long id, String username, Double weight, Double height, BigDecimal bodyFat);
}
