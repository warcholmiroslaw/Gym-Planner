package com.example.demo.controller;


import com.example.demo.dtos.WPSetsDto;
import com.example.demo.dtos.WorkoutPlanDto;
import com.example.demo.dtos.WorkoutPlanExerciseDto;
import com.example.demo.models.WorkoutPlan;
import com.example.demo.services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workoutplan")
public class WorkoutPlanController {


    @Autowired
    private WorkoutPlanService workoutPlanService;

    private final JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(WorkoutPlanController.class);
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private WorkoutPlanExerciseService workoutPlanExerciseService;

    @Autowired
    private SetsService setsService;


    public WorkoutPlanController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/my")
    public ResponseEntity<Object> getAllWorkoutPlan(HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        return  workoutPlanService.getAllWorkoutPlans(userId);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getWorkoutPlanByName(@PathVariable String name, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        return workoutPlanService.getWorkoutPlanByName(name, userId);
    }

    @DeleteMapping("delete/{workoutPlanId}")
    public ResponseEntity<Object> deleteWorkoutPlan(@PathVariable Integer workoutPlanId, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        return workoutPlanService.deleteWorkoutPlan(workoutPlanId, userId);
    }

    @PostMapping("/add")
    public ResponseEntity<WorkoutPlan> addWorkoutPlan(@RequestBody WorkoutPlanDto workoutPlanDto, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        WorkoutPlan newWorkoutPlan = workoutPlanService.createWorkoutPlan(workoutPlanDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateWorkoutPlan(@RequestBody WorkoutPlanDto workoutPlanDto, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        return workoutPlanService.updateWorkoutPlan(workoutPlanDto, userId);
    }
}
