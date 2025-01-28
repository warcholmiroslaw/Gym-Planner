package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDto {
    private Integer id;
    private Integer muscleGroupId;
    private String name;
    private String description;
}
