package com.FitnessTrack.MyFitnessTrack.services.productService;

import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductStatsUpdatedPerWeightDto;


public interface ProductStatsUpdatedPerWeightServices {
    ProductStatsUpdatedPerWeightDto findByIdAndUpdateStatsPerWeight(Long id);
}
