package com.FitnessTrack.MyFitnessTrack.services;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;

import java.util.List;
import java.util.Optional;

public interface UsersServices {

    List<PersonDto> findAllUsers();
    void deleteUserById(Long id);
    Person addUser(Person user);
    Optional<Person> findByUsername(String username);
}
