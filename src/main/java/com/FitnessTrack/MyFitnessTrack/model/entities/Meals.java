package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "meals")
@Entity
public class Meals {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    @JsonBackReference("person-meals")
    private Person person;

    @OneToMany(mappedBy = "meals", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("meals-items")
    private List<MealItem> mealItems;
}
