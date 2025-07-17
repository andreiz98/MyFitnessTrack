package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.FitnessTrack.MyFitnessTrack.model.MealType;
import com.FitnessTrack.MyFitnessTrack.model.entities.products.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "meal_items")
@Entity
public class MealItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "meals_id")
    private Meals meals;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
