package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "workout_plan_exercises")
@Data
public class WorkoutPlanExercise {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "wpe_id")
    @JsonIgnore
    private Integer id;

    @Column(name = "workout_plan_id")
    private Integer workoutPlanId;

    @Column(name = "exercise_id")
    private Integer exerciseId;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", referencedColumnName = "workout_plan_id",
            nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id",
            nullable = false, insertable = false, updatable = false)
    private Exercise exercise;

    @OneToMany(mappedBy = "workoutPlanExerciseId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutPlanExerciseSets> workoutPlanExerciseSets;


}
