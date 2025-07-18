package com.FitnessTrack.MyFitnessTrack.model.entities.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product_stats_updated")
@Entity
public class ProductStatsUpdatedPerWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "calories")
    private Double calories;
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private ProductStats productStats;
}
