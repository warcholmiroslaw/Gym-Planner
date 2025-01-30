package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutPlanExerciseDto {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer workoutPlanId;
    private Integer exerciseId;
    private String workoutPlanName;

}