package com.FitnessTrack.MyFitnessTrack.model.dto.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductUpdatedPerWeightDto implements Serializable {
    private Long id;
    private String name;
    private Double weight;
    private Double price;
}
