package com.FitnessTrack.MyFitnessTrack.services;


import com.FitnessTrack.MyFitnessTrack.model.dto.ProductUpdatedPerWeightDto;

public interface ProductUpdatedPerWeightServices {
    ProductUpdatedPerWeightDto findByIdAndUpdatePricePerWeight(Long id, Double weight);
}
