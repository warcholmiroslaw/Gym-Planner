package com.example.demo.repository;

import com.example.demo.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    Optional<Exercise> findByName(String name);

    Optional<List<Exercise>> findByUserId(Integer userId);
}
