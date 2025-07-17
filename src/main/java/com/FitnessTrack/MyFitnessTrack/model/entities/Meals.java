package com.FitnessTrack.MyFitnessTrack.model.entities;

import com.FitnessTrack.MyFitnessTrack.model.MealType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "meals", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealItem> mealItem;
}
