package com.FitnessTrack.MyFitnessTrack.model.dto.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private Long id;
    private String name;
    private Double weight;
    private Double price;
    private ProductStatsDto stats;
}
