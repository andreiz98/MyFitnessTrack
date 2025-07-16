package com.FitnessTrack.MyFitnessTrack.controllers;

import com.FitnessTrack.MyFitnessTrack.model.dto.MealItemDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.MealsDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductStatsUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.model.dto.product.ProductUpdatedPerWeightDto;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.MealService;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.product.ProductStatsUpdatedPerWeightService;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.product.ProductUpdatedPerWeightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealsController {

    private final MealService mealService;
    private final ProductUpdatedPerWeightService updatedPerWeightService;
    private final ProductStatsUpdatedPerWeightService statsUpdatedPerWeightService;

    @Autowired
    public MealsController(MealService mealService,
                           ProductUpdatedPerWeightService updatedPerWeightService,
                           ProductStatsUpdatedPerWeightService statsUpdatedPerWeightService) {
        this.mealService = mealService;
        this.updatedPerWeightService = updatedPerWeightService;
        this.statsUpdatedPerWeightService = statsUpdatedPerWeightService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<MealsDto> addMeals(@PathVariable Long id,
                                          @RequestBody @Valid List<MealItemDto> mealItem){
        MealsDto mealsDto = mealService.addMeals(id, mealItem);
        return ResponseEntity.ok(mealsDto);
    }

    @GetMapping
    public ResponseEntity<List<MealsDto>> findAllMeals(){
        return ResponseEntity.ok(mealService.findAllMeals());
    }

    @PutMapping("/productInfo/{id}")
    public ResponseEntity<ProductUpdatedPerWeightDto> findByIdAndUpdatePricePerWeight(@PathVariable Long id,
                                                                                      @RequestParam Double weight) {
        return ResponseEntity.ok(updatedPerWeightService.findByIdAndUpdatePricePerWeight(id, weight));
    }

    @GetMapping("/productStats/{id}")
    public ResponseEntity<ProductStatsUpdatedPerWeightDto> findByIdAndUpdateStatsPerWeight(@PathVariable Long id){
        return ResponseEntity.ok(statsUpdatedPerWeightService.findByIdAndUpdateStatsPerWeight(id));
    }
}
