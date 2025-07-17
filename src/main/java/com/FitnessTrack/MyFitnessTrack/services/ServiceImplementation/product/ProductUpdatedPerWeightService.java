package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.product;

import com.FitnessTrack.MyFitnessTrack.ClassMapper;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductUpdatedPerWeightRepository;
import com.FitnessTrack.MyFitnessTrack.services.productService.ProductUpdatedPerWeightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdatedPerWeightService implements ProductUpdatedPerWeightServices {

    private final ProductRepository productRepository;
    private final ProductUpdatedPerWeightRepository perWeightRepository;
    private final ClassMapper mapper;

    @Autowired
    public ProductUpdatedPerWeightService(ProductRepository productRepository,
                                          ProductUpdatedPerWeightRepository perWeightRepository,
                                          ClassMapper mapper) {
        this.productRepository = productRepository;
        this.perWeightRepository = perWeightRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductUpdatedPerWeightDto findByIdAndUpdatePricePerWeight(Long id, Double weight) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        ProductUpdatedPerWeight updatedPerWeight = perWeightRepository.findById(id)
                .orElse(new ProductUpdatedPerWeight());

        Double newPrice = (weight * product.getPrice()) / product.getWeight();
        updatedPerWeight.setProduct(product);
        updatedPerWeight.setName(product.getName());
        updatedPerWeight.setWeight(weight);
        updatedPerWeight.setPrice(newPrice);
        return mapper.toDto(perWeightRepository.save(updatedPerWeight));
    }
}
