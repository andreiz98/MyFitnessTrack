package com.FitnessTrack.MyFitnessTrack.repositories;

import com.FitnessTrack.MyFitnessTrack.model.entities.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealItemRepository extends JpaRepository<MealItem,Long> {
}
