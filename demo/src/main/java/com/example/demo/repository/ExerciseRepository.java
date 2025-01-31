package com.example.demo.repository;

import com.example.demo.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query("SELECT e FROM Exercise e WHERE e.name = :name AND (e.userId IS NULL  OR e.userId = :userId)")
    Optional<Exercise> findByName(
            @Param("name") String name,
            @Param("userId") Integer userId);

    @Query("SELECT e FROM Exercise e WHERE e.userId = :userId")
    Optional<List<Exercise>> findByUserId(@Param("userId")Integer userId);

    @Query("SELECT e FROM Exercise e WHERE e.userId = NULL")
    Optional<List<Exercise>> findAllGlobalExercises();

    @Query("SELECT e FROM Exercise e WHERE e.muscleGroup.name = :muscleGroupName AND (e.userId = :userId OR e.userId IS NULL)")
    Optional<List<Exercise>> findExercisesByMuscleGroupName(
                @Param("muscleGroupName")String muscleGroupName,
                @Param("userId") Integer userId
    );

    @Query("SELECT e FROM Exercise e WHERE e.userId = :userId OR e.userId IS NULL")
    Optional<List<Exercise>> findAll(@Param("userId") Integer userId);


    @Query("SELECT e FROM Exercise e WHERE e.id = :exerciseId AND (e.userId IS NULL  OR e.userId = :userId)")
    Optional<Exercise> findByIds(
            @Param("exerciseId") Integer exerciseId,
            @Param("userId") Integer userId);
}
