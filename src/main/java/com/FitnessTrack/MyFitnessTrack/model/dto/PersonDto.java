package com.FitnessTrack.MyFitnessTrack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto implements Serializable {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 16, message = "Minimum age is 16")
    private Integer age;
    @NotBlank(message = "Username is required")
    private String username;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Pattern(regexp = "[MmFf]", message = "Sex must be 'M' or 'F'")
    private String sex;
    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be greater than 0")
    private Double weight;
    @NotNull(message = "Height is required")
    @Positive(message = "Height must be greater than 0")
    private Double height;
    private Double bodyFat;

    //set custom objective
    @NotBlank(message = "Objective is required")
    private String objective;
    @NotBlank(message = "Activity is required")
    private String activity;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fats;
}
