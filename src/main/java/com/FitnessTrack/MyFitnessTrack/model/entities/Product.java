package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;

    @OneToOne
    @JoinColumn(name = "stats_id",referencedColumnName = "id")
    @JsonManagedReference
    private ProductStats stats;
}
