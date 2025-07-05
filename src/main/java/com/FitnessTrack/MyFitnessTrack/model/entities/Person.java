package com.FitnessTrack.MyFitnessTrack.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "person")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "sex")
    private char sex;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "height")
    private Double height;
    @Column(name = "body_fat", precision = 5, scale = 2)
    private BigDecimal bodyFat;
}
