package com.FitnessTrack.MyFitnessTrack.model.dto;

import com.FitnessTrack.MyFitnessTrack.model.MealType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealItemDto {
    private Long id;
    private String name;
    private Double weight;
    private Double price;
    private Long productId;
}
