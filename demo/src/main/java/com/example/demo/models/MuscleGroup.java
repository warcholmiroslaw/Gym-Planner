package com.example.demo.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "muscle_groups")
@Data
public class MuscleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
