package com.example.demo.controller;

import com.example.demo.dtos.ExerciseDto;
import com.example.demo.services.ExerciseService;
import com.example.demo.models.Exercise;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    private final JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(ExerciseController.class);
    @Autowired
    private UserService userService;

    public ExerciseController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    // get all global and user exercises
    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises(HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        return new ResponseEntity<>(exerciseService.allExercises(userId), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Exercise> getExerciseByName(@PathVariable String name, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        return new ResponseEntity<Exercise>(exerciseService.getExerciseByName(name, userId), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Exercise> updateExercise(@RequestBody ExerciseDto exerciseDto, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);
        Exercise updatedExercise = exerciseService.updateExercise(exerciseDto, userId);

        return ResponseEntity.ok(updatedExercise);

    }
    // add new exercise that belong to user
    @PostMapping("/add")
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseDto exerciseDto, HttpServletRequest request) {
        Integer userId = jwtService.extractUserId(request);

        Exercise newExercise = exerciseService.createExercise(exerciseDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExercise);
    }

    // get all user and global exercises by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Exercise>> getExerciseByCategory(@PathVariable String category, HttpServletRequest request) {

        // extract userId to collect all global and user exercises
        Integer userId = jwtService.extractUserId(request);

        List<Exercise> exercises = exerciseService.getExerciseByCategory(category, userId)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No exercises found for category: " + category));

        log.info("Found exercises: {}", exercises);

        return ResponseEntity.ok(exercises);
    }
}
