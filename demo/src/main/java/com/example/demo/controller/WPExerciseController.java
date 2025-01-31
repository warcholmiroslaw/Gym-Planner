package com.example.demo.controller;

import com.example.demo.dtos.WorkoutPlanExerciseDto;
import com.example.demo.services.JwtService;
import com.example.demo.services.WorkoutPlanExerciseService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/workoutplan/{workoutPlanId}/exercise")
public class WPExerciseController {

    @Autowired
    private WorkoutPlanExerciseService workoutPlanExerciseService;

    private final JwtService jwtService;

    public WPExerciseController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    private Integer workoutPlanId;
    private Integer userId;


    private static final Logger log = LoggerFactory.getLogger(WPExerciseController.class);

    @ModelAttribute
    public void setVariables(@PathVariable("workoutPlanId") Integer workoutPlanId, HttpServletRequest request) {

        this.workoutPlanId = workoutPlanId;
        this.userId = jwtService.extractUserId(request);

    }

    // add existing Exercise do workoutPlan
    @PostMapping("/{exerciseId}")
    public ResponseEntity<Object> addExerciseToWorkoutPlan(@PathVariable Integer exerciseId) {

        log.info("Try to add exercise to existing workout plan");

        return workoutPlanExerciseService.addWorkoutPlanExercise(this.workoutPlanId, exerciseId, this.userId);
    }


    // delete exercise from workoutPlan
    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Object> deleteWorkoutPlanExercise(@PathVariable Integer exerciseId) {

        log.info("Try delete exercise from existing workout plan");

        return workoutPlanExerciseService.deleteWorkoutPlanExercise(this.workoutPlanId, exerciseId, this.userId);

    }

}
