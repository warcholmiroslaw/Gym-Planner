package com.example.demo.services;

import com.example.demo.dtos.ExerciseDto;
import com.example.demo.models.Exercise;
import com.example.demo.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
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

    public Exercise createExercise(ExerciseDto exerciseDto, Integer userId) {

        Exercise newExercise = new Exercise()
                .setUserId(userId)
                .setName(exerciseDto.getName())
                .setDescription(exerciseDto.getDescription())
                .setMuscleGroupId(exerciseDto.getMuscleGroupId());

        return exerciseRepository.save(newExercise);
    }

    public Exercise updateExercise(ExerciseDto exerciseDto, Integer userId) {
        Exercise existingExercise = exerciseRepository.findById(exerciseDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));


        // model mapper allows to update only that values that were inserted
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        //update only these values that were inserted
        modelMapper.map(exerciseDto, existingExercise);

        return exerciseRepository.save(existingExercise);
    }
}
