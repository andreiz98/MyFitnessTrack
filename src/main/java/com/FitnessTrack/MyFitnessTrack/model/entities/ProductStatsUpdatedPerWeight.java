package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product_info")
@Entity
public class ProductStatsUpdatedPerWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "energy_value")
    private Double energyValue;
    @Column(name = "fats")
    private Double fats;
    @Column(name = "carbs")
    private Double carbs;
    @Column(name = "sugar")
    private Double sugar;
    @Column(name = "fiber")
    private Double fiber;
    @Column(name = "protein")
    private Double protein;
    @Column(name = "salt")
    private Double salt;

    @OneToOne(mappedBy = "updatedPerWeight")
    @JsonBackReference
    private ProductStats productStats;
}
