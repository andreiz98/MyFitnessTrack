package com.FitnessTrack.MyFitnessTrack.controllers;

import com.FitnessTrack.MyFitnessTrack.model.dto.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.ProductService;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.ProductUpdatedPerWeightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/productList")
public class ProductController {

    private final ProductService service;
    private final ProductUpdatedPerWeightService updatedPerWeightService;

    @Autowired
    public ProductController(ProductService service,
                             ProductUpdatedPerWeightService updatedPerWeightService) {
        this.service = service;
        this.updatedPerWeightService = updatedPerWeightService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/productByName")
    public ResponseEntity<List<ProductDto>> findByName(@RequestParam String productName) {
        return ResponseEntity.ok(service.findByNameContainingIgnoreCase(productName));
    }

    @PutMapping("/productInfo/{id}")
    public ResponseEntity<ProductUpdatedPerWeightDto> findByIdAndUpdatePricePerWeight(@PathVariable Long id,
                                                                                      @RequestParam Double weight) {
        ProductUpdatedPerWeightDto product = updatedPerWeightService.findByIdAndUpdatePricePerWeight(id, weight);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(service.addProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Double price) {
        return ResponseEntity.ok(service.updateProduct(id, name, price));
    }
}
