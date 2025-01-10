package com.example.demo.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exercises")
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "muscle_group_id")
    private Integer muscleGroupId;

    private String name;

    private String description;
}
