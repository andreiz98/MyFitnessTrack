package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation;

import com.FitnessTrack.MyFitnessTrack.ClassMapper;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealItemDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.model.entities.MealItem;
import com.FitnessTrack.MyFitnessTrack.model.entities.Meals;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.repositories.PersonRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.MealsRepository;
import com.FitnessTrack.MyFitnessTrack.repositories.product.ProductRepository;
import com.FitnessTrack.MyFitnessTrack.services.MealServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MealService implements MealServices {

    private final PersonRepository repository;
    private final MealsRepository mealsRepository;
    private final ProductRepository productRepository;
    private final ClassMapper mapper;

    @Autowired
    public MealService(PersonRepository repository,
                       MealsRepository mealsRepository,
                       ProductRepository productRepository,
                       ClassMapper mapper) {
        this.repository = repository;
        this.mealsRepository = mealsRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Double calculatePricePerWeight(String name, Double weight) {
        List<Product> products = productRepository.findByName(name);

        if (products.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        try {
            Product product = products.stream()
                    .filter(p -> p.getWeight() >= weight)
                    .min(Comparator.comparing(p -> p.getWeight() - weight))
                    .orElseThrow(() -> new RuntimeException("No product with enough weight"));

            return (weight * product.getPrice()) / product.getWeight();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MealsDto addMeal(Long id, MealsDto mealsDto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Meals meals = new Meals();
        meals.setPerson(person);
        meals.setMealType(mealsDto.getMealType());
        meals.setMealItem(new ArrayList<>());

        for (MealItemDto itemDto : mealsDto.getMealItem()) {
            List<Product> products = productRepository.findByName(itemDto.getName());

            Product product = products.stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No product with name: " + itemDto.getName()));

            MealItem mealItem = new MealItem();
            mealItem.setName(product.getName());
            mealItem.setWeight(itemDto.getWeight());
            mealItem.setPrice(calculatePricePerWeight(mealItem.getName(), mealItem.getWeight()));
            mealItem.setMeals(meals);
            mealItem.setProduct(product);
            meals.getMealItem().add(mealItem);
        }

        person.getMeals().add(meals);
        repository.save(person);

        return mapper.toDto(meals);
    }

    @Override
    public List<MealsDto> findAllMeals() {
        return mealsRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}