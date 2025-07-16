package com.FitnessTrack.MyFitnessTrack.model.dto;

import com.FitnessTrack.MyFitnessTrack.model.MealType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealItemDto {

    private String name;
    private Double weight;
    private Double price;

    @NotNull(message = "Meal type is required")
    private MealType mealType;

}
