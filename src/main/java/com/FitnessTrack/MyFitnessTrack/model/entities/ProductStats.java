package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "product_stats")
@Entity
public class ProductStats {

    @Id
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
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productStats")
    @JsonManagedReference
    private ProductStatsUpdatedPerWeight statsUpdatedPerWeight;

}
