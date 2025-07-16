package com.FitnessTrack.MyFitnessTrack.repositories.product;

import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContainingIgnoreCase(String name);
    Optional<Product> findByName(String name);
}
