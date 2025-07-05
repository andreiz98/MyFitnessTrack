package com.FitnessTrack.MyFitnessTrack.repositories;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByUsername(String username);
}
