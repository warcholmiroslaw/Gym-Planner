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

    @Column(name = "user_id", nullable = true) //if null exercise is available to every user
    private Integer userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "muscle_group_id")
    private MuscleGroup muscleGroup;

    private String name;

    private String description;
}
