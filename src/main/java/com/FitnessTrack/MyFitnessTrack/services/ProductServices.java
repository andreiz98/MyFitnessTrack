package com.FitnessTrack.MyFitnessTrack.services;

import com.FitnessTrack.MyFitnessTrack.model.dto.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Product;

import java.util.List;

public interface ProductServices {
    List<ProductDto> findAllProducts();
    void deleteProductById(Long id);
    Product addProduct(Product product);
    List<Product> findByNameContainingIgnoreCase(String name);
    ProductDto updateProduct(Long id, String name, Double price);

}
