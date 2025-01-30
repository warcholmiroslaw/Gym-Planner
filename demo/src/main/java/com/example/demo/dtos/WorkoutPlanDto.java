package com.example.demo.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutPlanDto {
    private Integer id;
    private Integer userId;
    private String name;
    private String description;
    private java.time.LocalDateTime createdAt;

}
