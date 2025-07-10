package com.FitnessTrack.MyFitnessTrack.services;

import com.FitnessTrack.MyFitnessTrack.model.dto.ProductStatsUpdatedPerWeightDto;


public interface ProductStatsUpdatedPerWeightServices {
    ProductStatsUpdatedPerWeightDto findByIdAndUpdateStatsPerWeight(Long id);
}
