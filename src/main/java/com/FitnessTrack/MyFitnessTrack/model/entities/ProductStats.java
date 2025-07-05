package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product_info")
@Entity
public class ProductStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "energy_value")
    private Long energyValue;
    @Column(name = "fats")
    private Double fats;
    @Column(name = "farbs")
    private Double carbs;
    @Column(name = "sugar")
    private Double sugar;
    @Column(name = "fiber")
    private Double fiber;
    @Column(name = "protein")
    private Double protein;
    @Column(name = "salt")
    private Double salt;

    @OneToOne(targetEntity = Product.class)
    @JsonManagedReference
    private Product product;

}
