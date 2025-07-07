package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation;

import com.FitnessTrack.MyFitnessTrack.model.entities.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.repositories.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.ProductUpdatedPerWeightRepository;
import com.FitnessTrack.MyFitnessTrack.services.ProductUpdatedPerWeightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdatedPerWeightService implements ProductUpdatedPerWeightServices {

    private final ProductRepository productRepository;
    private final ProductUpdatedPerWeightRepository perWeightRepository;

    @Autowired
    public ProductUpdatedPerWeightService(ProductRepository productRepository, ProductUpdatedPerWeightRepository perWeightRepository) {
        this.productRepository = productRepository;
        this.perWeightRepository = perWeightRepository;
    }

    @Override
    public ProductUpdatedPerWeight findByIdAndUpdatePricePerWeight(Long id, Double weight) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        ProductUpdatedPerWeight updatedPerWeight = new ProductUpdatedPerWeight();
        Double newPrice = (weight * product.getPrice()) / product.getWeight();
        updatedPerWeight.setName(product.getName());
        updatedPerWeight.setWeight(weight);
        updatedPerWeight.setPrice(newPrice);
        return perWeightRepository.save(updatedPerWeight);
    }
}
