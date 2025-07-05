package com.FitnessTrack.MyFitnessTrack.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PersonDto implements Serializable {
    private Long id;
    private String name;
    private String lastname;
    private String username;
    private Date dateOfBirth;
    private String sex;
    private Double weight;
    private Double height;
    private BigDecimal bodyFat;
}
