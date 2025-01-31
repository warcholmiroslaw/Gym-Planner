package com.example.demo.repository;

import com.example.demo.models.WorkoutPlanSets;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SetsRepository extends JpaRepository<WorkoutPlanSets, Integer> {


//    @Query("""
//            SELECT COUNT(wps)
//            FROM WorkoutPlanSets wps
//            JOIN WorkoutPlanExercise wpe
//                ON wps.workoutPlanExerciseId = wpe.id
//            WHERE wpe.exerciseId = :exerciseId
//            AND wpe.workoutPlanId = :workoutPlanId""")
//    Integer countWorkoutPlanSetsByExerciseAndPlan(
//            @Param("exerciseId")Integer exerciseId,
//            @Param("workoutPlanId")Integer workoutPlanId);

    @Query("""
    SELECT COUNT(wps.workoutPlanExerciseId) 
    FROM WorkoutPlanSets wps 
    JOIN WorkoutPlanExercise wpe ON wps.workoutPlanExerciseId = wpe.id
    JOIN WorkoutPlan wp ON wpe.workoutPlanId = wp.id
    WHERE wpe.id = :wpeId 
    AND wpe.workoutPlanId = :PlanId
    AND wp.userId = :userId
""")
    Integer countWorkoutPlanSetsByExerciseAndPlan(
            @Param("wpeId") Integer wpeId,
            @Param("PlanId") Integer PlanId,
            @Param("userId") Integer userId
    );


    @Modifying
    @Transactional
    @Query("""
    DELETE FROM WorkoutPlanSets wps
    WHERE wps.workoutPlanExerciseId IN (
            SELECT wpe.id
                    FROM WorkoutPlanExercise wpe
                    JOIN WorkoutPlan wp ON wpe.workoutPlanId = wp.id
                    WHERE wpe.id = :wpeId
                    AND wpe.workoutPlanId = :planId
                    AND (wp.userId = :userId OR wp.userId IS NULL)
        )
    AND wps.setNumber = :setNumber
    """)
    void deleteByIds(
            @Param("setNumber") Integer setNumber,
            @Param("wpeId") Integer wpeId,
            @Param("planId") Integer planId,
            @Param("userId") Integer userId
    );
}