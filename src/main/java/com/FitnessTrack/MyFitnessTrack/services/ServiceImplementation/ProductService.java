package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation;

import com.FitnessTrack.MyFitnessTrack.model.dto.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductStats;
import com.FitnessTrack.MyFitnessTrack.model.entities.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.repositories.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.services.ProductServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServices {

    private final ProductRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public ProductService(ProductRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private ProductDto entityToDto(Product product) {
        return mapper.convertValue(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return repository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product addProduct(Product product) {
        if (product.getWeight() == null && product.getPrice() == null) {
            throw new RuntimeException("Price and weight can t be null");
        } else {
            return repository.save(product);
        }
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);

    }

    @Override
    public ProductDto updateProduct(Long id, String name, Double price) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        if (name != null) {
            product.setName(name);
        }
        if (price != null) {
            product.setPrice(price);
        }

        return mapper.convertValue(repository.save(product), ProductDto.class);
    }

}
