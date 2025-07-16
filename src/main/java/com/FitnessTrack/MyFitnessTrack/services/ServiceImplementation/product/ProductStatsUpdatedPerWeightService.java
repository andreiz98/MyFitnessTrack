package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.product;

import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductStatsUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductStats;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductStatsUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductStatsUpdatedPerWeightRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductUpdatedPerWeightRepository;
import com.FitnessTrack.MyFitnessTrack.services.productService.ProductStatsUpdatedPerWeightServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStatsUpdatedPerWeightService implements ProductStatsUpdatedPerWeightServices {

    private final ProductRepository productRepository;
    private final ProductStatsUpdatedPerWeightRepository statsUpdatedPerWeightRepository;
    private final ProductUpdatedPerWeightRepository updatedPerWeightRepository;
    private final ObjectMapper mapper;

    @Autowired
    public ProductStatsUpdatedPerWeightService(ProductRepository productRepository,
                                               ProductStatsUpdatedPerWeightRepository statsUpdatedPerWeightRepository, 
                                               ProductUpdatedPerWeightRepository updatedPerWeightRepository,
                                               ObjectMapper mapper) {
        this.productRepository = productRepository;
        this.statsUpdatedPerWeightRepository = statsUpdatedPerWeightRepository;
        this.updatedPerWeightRepository = updatedPerWeightRepository;
        this.mapper = mapper;
    }

    private Double scale(Double originalValue, Double targetWeight) {
        if (originalValue == null) return 0.0;
        return (originalValue / 100) * targetWeight;
    }

    @Override
    public ProductStatsUpdatedPerWeightDto findByIdAndUpdateStatsPerWeight(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        ProductStats productStats = product.getStats();
        ProductUpdatedPerWeight productUpdatedPerWeight = updatedPerWeightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Updated weight not found for product with ID: " + id));

        ProductStatsUpdatedPerWeight statsUpdatedPerWeight = statsUpdatedPerWeightRepository.findById(id)
                .orElse(new ProductStatsUpdatedPerWeight());

        statsUpdatedPerWeight.setProductStats(productStats);
        statsUpdatedPerWeight.setCalories(scale(productStats.getCalories(), productUpdatedPerWeight.getWeight()));
        statsUpdatedPerWeight.setFats(scale(productStats.getFats(), productUpdatedPerWeight.getWeight()));
        statsUpdatedPerWeight.setCarbs(scale(productStats.getCarbs(), productUpdatedPerWeight.getWeight()));
        statsUpdatedPerWeight.setSugar(scale(productStats.getSugar(), productUpdatedPerWeight.getWeight()));
        statsUpdatedPerWeight.setFiber(scale(productStats.getFiber(), productUpdatedPerWeight.getWeight()));
        statsUpdatedPerWeight.setProtein(scale(productStats.getProtein(), productUpdatedPerWeight.getWeight()));
        statsUpdatedPerWeight.setSalt(scale(productStats.getSalt(), productUpdatedPerWeight.getWeight()));

        return mapper.convertValue(statsUpdatedPerWeightRepository.save(statsUpdatedPerWeight), ProductStatsUpdatedPerWeightDto.class);
    }
}
