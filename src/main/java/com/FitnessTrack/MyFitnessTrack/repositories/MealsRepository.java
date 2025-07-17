package com.FitnessTrack.MyFitnessTrack.repositories;

import com.FitnessTrack.MyFitnessTrack.model.entities.Meals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealsRepository extends JpaRepository<Meals,Long> {
//    List<Meals> findByPerson(Person person);
}
