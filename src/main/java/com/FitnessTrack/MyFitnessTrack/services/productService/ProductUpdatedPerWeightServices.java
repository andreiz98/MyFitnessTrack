package com.FitnessTrack.MyFitnessTrack.services.productService;


import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductUpdatedPerWeightDto;

public interface ProductUpdatedPerWeightServices {
    ProductUpdatedPerWeightDto findByIdAndUpdatePricePerWeight(Long id, Double weight);
}
