package com.FitnessTrack.MyFitnessTrack.model.entities.products;

import com.FitnessTrack.MyFitnessTrack.model.entities.MealItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products_updated")
@Entity
public class ProductUpdatedPerWeight {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Product product;
}
