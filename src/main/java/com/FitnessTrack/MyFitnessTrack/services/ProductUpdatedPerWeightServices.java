package com.FitnessTrack.MyFitnessTrack.services;


import com.FitnessTrack.MyFitnessTrack.model.dto.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductUpdatedPerWeight;

public interface ProductUpdatedPerWeightServices {
    ProductUpdatedPerWeightDto findByIdAndUpdatePricePerWeight(Long id, Double weight);
}
