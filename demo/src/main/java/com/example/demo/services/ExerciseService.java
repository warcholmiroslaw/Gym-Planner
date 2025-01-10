package com.example.demo.services;

import com.example.demo.models.Exercise;
import com.example.demo.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> allExercises() {
        return exerciseRepository.findAll();
    }


    public Optional<Exercise> getExerciseByName(String name) {
        return exerciseRepository.findByName(name);
    }

    public Optional<List<Exercise>> getExerciseByUserId(Integer userId) {
        return exerciseRepository.findByUserId(userId);
    }
}
