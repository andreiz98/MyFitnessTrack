package com.FitnessTrack.MyFitnessTrack.model.dto;

import com.FitnessTrack.MyFitnessTrack.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealsDto {
    private Long personId;
    private Long id;
    private MealType mealType;
    private List<MealItemDto> mealItem;
}
