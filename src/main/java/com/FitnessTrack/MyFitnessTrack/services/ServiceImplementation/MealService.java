package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation;

import com.FitnessTrack.MyFitnessTrack.model.dto.MealItemDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.model.entities.MealItem;
import com.FitnessTrack.MyFitnessTrack.model.entities.Meals;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.repositories.PersonRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.MealsRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductUpdatedPerWeightRepository;
import com.FitnessTrack.MyFitnessTrack.services.MealServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService implements MealServices {

    private final PersonRepository repository;
    private final MealsRepository mealsRepository;
    private final ProductRepository productRepository;
    private ObjectMapper mapper;

    @Autowired
    public MealService(PersonRepository repository,
                       MealsRepository mealsRepository,
                       ProductRepository productRepository,
                       ObjectMapper mapper) {
        this.repository = repository;
        this.mealsRepository = mealsRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public MealItemDto toDto(MealItem item) {
        return mapper.convertValue(item, MealItemDto.class);
    }

    public MealsDto toDto(Meals meals) {
        List<MealItemDto> items = meals.getMealItems()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new MealsDto(meals.getPerson().getId(), items);
    }

    public Double calculatePricePerWeight(String name, Double weight) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product name not found: " + name));

        if (product.getWeight() < weight) {
            throw new RuntimeException("Product weight < " + weight);
        }

        return (weight * product.getPrice()) / product.getWeight();
    }

    @Override
    public List<MealsDto> findAllMeals() {
        return mealsRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional
    public MealsDto addMeals(Long id, List<MealItemDto> mealItem) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        System.out.println("Person id = " + person.getId());

        //check if meals already exist else create a new one
        Meals meals = mealsRepository.findByPerson(person).orElse(null);

        if (meals == null) {
            meals = new Meals();
            meals.setPerson(person);// MapsId automatically sets ID
            meals.setMealItems(new ArrayList<>());
        } else {
            // Clear old items to avoid stale updates
            meals.getMealItems().clear();
        }

        Meals finalMeals = meals;
        List<MealItem> mealItems = mealItem.stream()
                        .map(dto->{
                            MealItem Item = mapper.convertValue(dto, MealItem.class);

                            // Calculate and set price here:
                            Item.setPrice(calculatePricePerWeight(dto.getName(), dto.getWeight()));
                            Item.setMeals(finalMeals);

                            return Item;
                        })
                .toList();
        meals.getMealItems().addAll(mealItems);

        return toDto(mealsRepository.save(meals));
    }
}
