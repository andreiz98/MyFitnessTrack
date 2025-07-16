package com.FitnessTrack.MyFitnessTrack.controllers;

import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productList")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/productByName")
    public ResponseEntity<List<Product>> findByName(@RequestParam String productName) {
        return ResponseEntity.ok(service.findByNameContainingIgnoreCase(productName));
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
