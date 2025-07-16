package com.FitnessTrack.MyFitnessTrack.repositories;

import com.FitnessTrack.MyFitnessTrack.model.entities.Meals;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealsRepository extends JpaRepository<Meals,Long> {
    Optional<Meals> findByPerson(Person person);
}
