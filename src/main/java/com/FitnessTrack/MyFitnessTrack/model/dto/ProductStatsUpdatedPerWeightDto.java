package com.FitnessTrack.MyFitnessTrack.model.dto;


import lombok.Data;

@Data
public class ProductStatsUpdatedPerWeightDto {

    private Long id;
    private Double calories;
    private Double fats;
    private Double carbs;
    private Double sugar;
    private Double fiber;
    private Double protein;
    private Double salt;
}
