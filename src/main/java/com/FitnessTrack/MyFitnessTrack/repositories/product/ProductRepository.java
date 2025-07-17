package com.FitnessTrack.MyFitnessTrack.repositories.product;

import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByName(String name);
}
