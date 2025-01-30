package com.example.demo.services;

import com.example.demo.dtos.WorkoutPlanExerciseDto;
import com.example.demo.models.Exercise;
import com.example.demo.models.WorkoutPlan;
import com.example.demo.models.WorkoutPlanExercise;
import com.example.demo.repository.WorkoutPlanExerciseRepository;
import com.example.demo.repository.WorkoutPlanExerciseSetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutPlanExerciseService {
    @Autowired
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutPlanExerciseSetsRepository workoutPlanExerciseSetsRepository;


    private static final Logger logger = LoggerFactory.getLogger(WorkoutPlanExerciseService.class);
    @Autowired
    private WorkoutPlanService workoutPlanService;

    // get all exercises in the given training plan


    // update exercises in existing workout plan



    // add exercise to existing workout plan
    public ResponseEntity<Object> addWorkoutPlanExercise(Integer workoutPlanId, WorkoutPlanExerciseDto workoutPlanExerciseDto, Integer userId) {

        Exercise existingExercise = exerciseService.getExerciseByName(workoutPlanExerciseDto.getName(), userId);

        if (existingExercise == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WorkoutPlanExercise workoutPlanExercise = new WorkoutPlanExercise()
                .setWorkoutPlanId(workoutPlanId)
                .setExerciseId(workoutPlanExerciseDto.getExerciseId());

        return new ResponseEntity<>(workoutPlanExerciseRepository.save(workoutPlanExercise), HttpStatus.CREATED);
    }


    // delete exercise from existing workout plan
    public ResponseEntity<Object> deleteWorkoutPlanExercise(Integer workoutPlanId,
                                                            WorkoutPlanExerciseDto workoutPlanExerciseDto,
                                                            Integer userId) {

        WorkoutPlanExercise workoutPlanExercise = workoutPlanExerciseRepository.findByIds(workoutPlanId, workoutPlanExerciseDto.getExerciseId());
        if (workoutPlanExercise == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        workoutPlanExerciseRepository.deleteById(workoutPlanExercise.getId());
        return ResponseEntity.noContent().build();
    }


}
