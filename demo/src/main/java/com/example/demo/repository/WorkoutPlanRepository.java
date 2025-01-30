package com.example.demo.repository;

import com.example.demo.models.User;
import com.example.demo.models.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Integer> {

    @Query("SELECT w FROM WorkoutPlan w WHERE w.userId = :userId")
    Optional<List<WorkoutPlan>> findAllWorkoutPlansByUserId(
            @Param("userId") Integer userId);

    @Query("SELECT w FROM WorkoutPlan w WHERE w.name = :name AND w.userId = :userId")
    Optional<WorkoutPlan> getWorkoutPlanByName(
            @Param("name")String name,
            @Param("userId") Integer userId);

    Integer user(User user);
}
