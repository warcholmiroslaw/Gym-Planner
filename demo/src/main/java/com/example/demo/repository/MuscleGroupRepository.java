package com.example.demo.repository;

import com.example.demo.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleGroupRepository extends JpaRepository<Exercise, Integer> {
}
