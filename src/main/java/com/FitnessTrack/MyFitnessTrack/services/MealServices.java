package com.FitnessTrack.MyFitnessTrack.services;

import com.FitnessTrack.MyFitnessTrack.model.dto.MealItemDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductDto;

import java.util.List;


public interface MealServices {
    MealsDto addMeals(Long id, List<MealItemDto> mealItem);
    List<MealsDto> findAllMeals();
}
