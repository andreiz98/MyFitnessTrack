package com.FitnessTrack.MyFitnessTrack.controllers;

import com.FitnessTrack.MyFitnessTrack.model.dto.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.ProductStatsUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductStatsUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.ProductService;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.ProductStatsUpdatedPerWeightService;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.ProductUpdatedPerWeightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/productList")
public class ProductController {

    private final ProductService service;
    private final ProductUpdatedPerWeightService updatedPerWeightService;
    private final ProductStatsUpdatedPerWeightService statsUpdatedPerWeightService;

    @Autowired
    public ProductController(ProductService service,
                             ProductUpdatedPerWeightService updatedPerWeightService,
                             ProductStatsUpdatedPerWeightService statsUpdatedPerWeightService) {
        this.service = service;
        this.updatedPerWeightService = updatedPerWeightService;
        this.statsUpdatedPerWeightService = statsUpdatedPerWeightService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/productByName")
    public ResponseEntity<List<Product>> findByName(@RequestParam String productName) {
        return ResponseEntity.ok(service.findByNameContainingIgnoreCase(productName));
    }

    @PutMapping("/productInfo/{id}")
    public ResponseEntity<ProductUpdatedPerWeightDto> findByIdAndUpdatePricePerWeight(@PathVariable Long id,
                                                                                      @RequestParam Double weight) {
        return ResponseEntity.ok(updatedPerWeightService.findByIdAndUpdatePricePerWeight(id, weight));
    }

    @PutMapping("/productStats/{id}")
    public ResponseEntity<ProductStatsUpdatedPerWeightDto> findByIdAndUpdateStatsPerWeight(@PathVariable Long id){
        return ResponseEntity.ok(statsUpdatedPerWeightService.findByIdAndUpdateStatsPerWeight(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product productDto) {
        return ResponseEntity.ok(service.addProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Double price) {
        return ResponseEntity.ok(service.updateProduct(id, name, price));
    }
}
