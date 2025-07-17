package com.FitnessTrack.MyFitnessTrack.services;

import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.MealItem;
import com.FitnessTrack.MyFitnessTrack.model.entities.Meals;

import java.util.List;


public interface MealServices {
    MealsDto addMeal(Long id, MealsDto mealsDto);
    List<MealsDto> findAllMeals();
}
