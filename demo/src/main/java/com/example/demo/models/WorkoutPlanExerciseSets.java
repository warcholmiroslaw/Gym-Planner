package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "workout_plan_sets")
@Data
public class WorkoutPlanExerciseSets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name="wps_id")
    private Integer id;

    @JsonIgnore
    @Column(name = "wpe_id")
    private Integer workoutPlanExerciseId;

    @Column(name = "set_number")
    private Integer setNumber;

    private Integer reps;

    private Double weight;


}
