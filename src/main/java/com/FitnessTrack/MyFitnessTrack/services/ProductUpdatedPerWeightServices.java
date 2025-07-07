package com.FitnessTrack.MyFitnessTrack.services;


import com.FitnessTrack.MyFitnessTrack.model.entities.ProductUpdatedPerWeight;

public interface ProductUpdatedPerWeightServices {
    ProductUpdatedPerWeight findByIdAndUpdatePricePerWeight(Long id, Double weight);
}
