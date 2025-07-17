package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.product;

import com.FitnessTrack.MyFitnessTrack.ClassMapper;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.services.productService.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServices {

    private final ProductRepository repository;
    private final ClassMapper mapper;

    @Autowired
    public ProductService(ProductRepository repository, ClassMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toDto)
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
    public List<ProductDto> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::toDto)
                .toList();
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

        return mapper.toDto(repository.save(product));
    }

}
