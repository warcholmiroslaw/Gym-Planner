package com.example.demo.services;

import com.example.demo.models.Exercise;
import com.example.demo.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;



    // returns all user and global exercises
    public List<Exercise> allExercises(Integer userId) {

        return exerciseRepository.findAll(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No exercises found for user ID: " + userId)
                );
    }



    private Optional<List<Exercise>> findAllGlobalExercises() {

        return exerciseRepository.findAllGlobalExercises();
    }

    public Exercise getExerciseByName(String name, Integer userId) {
        return exerciseRepository.findByName(name, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "There's no exercise: " + name)
                );
    }

    public Optional<List<Exercise>> getExerciseByCategory(String category, Integer userId) {
        return exerciseRepository.findExercisesByMuscleGroupName(category, userId);
    }

    public Optional<List<Exercise>> getExerciseByUserId(Integer userId) {
        return exerciseRepository.findByUserId(userId);
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
