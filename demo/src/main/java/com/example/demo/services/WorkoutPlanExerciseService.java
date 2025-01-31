package com.example.demo.services;

import com.example.demo.dtos.WorkoutPlanExerciseDto;
import com.example.demo.models.Exercise;
import com.example.demo.models.WorkoutPlanExercise;
import com.example.demo.repository.WorkoutPlanExerciseRepository;
import com.example.demo.repository.SetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanExerciseService {
    @Autowired
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Autowired
    private ExerciseService exerciseService;


    //private static final Logger logger = LoggerFactory.getLogger(WorkoutPlanExerciseService.class);

    // add exercise to existing workout plan
    public ResponseEntity<Object> addWorkoutPlanExercise(Integer workoutPlanId, Integer exerciseId, Integer userId) {

        Exercise existingExercise = exerciseService.getExerciseByIds(exerciseId, userId);

        if (existingExercise == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WorkoutPlanExercise workoutPlanExercise = new WorkoutPlanExercise()
                .setWorkoutPlanId(workoutPlanId)
                .setExerciseId(exerciseId);

        return new ResponseEntity<>(workoutPlanExerciseRepository.save(workoutPlanExercise), HttpStatus.CREATED);
    }


    // delete exercise from existing workout plan
    public ResponseEntity<Object> deleteWorkoutPlanExercise(Integer workoutPlanId,
                                                            Integer exerciseId,
                                                            Integer userId) {

        WorkoutPlanExercise workoutPlanExercise = workoutPlanExerciseRepository.findByIds(workoutPlanId, exerciseId);
        if (workoutPlanExercise == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        workoutPlanExerciseRepository.deleteById(workoutPlanExercise.getId());
        return ResponseEntity.noContent().build();
    }


}
