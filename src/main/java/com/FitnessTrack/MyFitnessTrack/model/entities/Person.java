package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.FitnessTrack.MyFitnessTrack.model.MealType;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "person")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "sex", nullable = false)
    private char sex;
    @Column(name = "weight", nullable = false)
    private Double weight;
    @Column(name = "height", nullable = false)
    private Double height;
    @Column(name = "body_fat")
    private Double bodyFat;

    //set custom objective
    @Column(name = "objective", nullable = false)
    private String objective;
    @Column(name = "activity", nullable = false)
    private String activity;
    @Column(name = "calories_needed")
    private Double calories;
    @Column(name = "protein")
    private Double protein;
    @Column(name = "carbs")
    private Double carbs;
    @Column(name = "fats")
    private Double fats;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meals> meals = new ArrayList<>();
}
