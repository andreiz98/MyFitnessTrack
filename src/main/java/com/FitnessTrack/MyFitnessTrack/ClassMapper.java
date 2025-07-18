package com.FitnessTrack.MyFitnessTrack;

import com.FitnessTrack.MyFitnessTrack.model.dto.MealItemDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductStatsDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductStatsUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.MealItem;
import com.FitnessTrack.MyFitnessTrack.model.entities.Meals;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductStats;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductStatsUpdatedPerWeight;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.ProductUpdatedPerWeight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(target = "age", expression = "java(calculateAge(person.getDateOfBirth()))")
    PersonDto toDto(Person person);

    @Mapping(target = "age", expression = "java(calculateAge(dto.getDateOfBirth()))")
    Person ToEntity(PersonDto dto);

    ProductDto toDto(Product product);

    ProductStatsDto toDto(ProductStats stats);

    ProductStatsUpdatedPerWeightDto toDto(ProductStatsUpdatedPerWeight productStatsUpdatedPerWeight);

    ProductUpdatedPerWeightDto toDto(ProductUpdatedPerWeight productUpdatedPerWeight);

    @Mapping(source = "product.id", target = "productId")
    MealItemDto toDto(MealItem mealItem);
    MealItem toEntity(MealItemDto dto);

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.username", target = "personUsername")
    MealsDto toDto(Meals meals);

    default int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
