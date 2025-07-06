package com.FitnessTrack.MyFitnessTrack.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PersonDto implements Serializable {
    private Long id;
    private String name;
    private String lastname;
    private Integer age;
    private String username;
    private LocalDate dateOfBirth;
    private String sex;
    private Double weight;
    private Double height;
    private BigDecimal bodyFat;
}
