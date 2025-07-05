package com.FitnessTrack.MyFitnessTrack.model.dto;

import lombok.Data;

@Data
public class ProductStatsDto {
    private Long id;
    private Long energyValue;
    private Double fats;
    private Double carbs;
    private Double sugar;
    private Double fiber;
    private Double protein;
    private Double salt;
}
