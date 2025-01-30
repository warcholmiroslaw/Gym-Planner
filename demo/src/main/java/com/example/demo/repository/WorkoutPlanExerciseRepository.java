package com.example.demo.repository;


import com.example.demo.models.WorkoutPlanExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkoutPlanExerciseRepository extends JpaRepository<WorkoutPlanExercise, Integer> {


    @Query("SELECT wpe FROM WorkoutPlanExercise wpe " +
            "WHERE wpe.exerciseId = :exerciseId AND wpe.workoutPlanId = :workoutPlanId")
    WorkoutPlanExercise findByIds(
            @Param("workoutPlanId")Integer workoutPlanId,
            @Param("exerciseId") Integer exerciseId);
}
