package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
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


    private Integer userId;

    public ExerciseController(JwtService jwtService) {

        this.jwtService = jwtService;
    }


    @ModelAttribute
    public void setVariables(HttpServletRequest request) {
        this.userId = jwtService.extractUserId(request);
    }

    // get all global and user exercises
    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises() {

        return new ResponseEntity<>(exerciseService.allExercises(this.userId), HttpStatus.OK);
    }

    // get exercise by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Exercise> getExerciseByName(@PathVariable String name) {

        return new ResponseEntity<Exercise>(exerciseService.getExerciseByName(name, this.userId), HttpStatus.OK);
    }
    // update Exercise
    @PutMapping
    public ResponseEntity<Exercise> updateExercise(@RequestBody ExerciseDto exerciseDto) {

        Exercise updatedExercise = exerciseService.updateExercise(exerciseDto, this.userId);

        return ResponseEntity.ok(updatedExercise);

    }

    // add new exercise that belong to user
    @PostMapping
    public ResponseEntity<Void> createExercise(@RequestBody ExerciseDto exerciseDto) {

        log.info("Try to add new exercise");
        log.info(exerciseDto.toString());
        Exercise newExercise = exerciseService.createExercise(exerciseDto, this.userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete exercise
    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Object> deleteExercise(@PathVariable Integer exerciseId) {

        log.info("Try to delete exercise");

        return exerciseService.deleteExercise(exerciseId, this.userId);
    }

    // get all user and global exercises by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Exercise>> getExerciseByCategory(@PathVariable String category) {

        List<Exercise> exercises = exerciseService.getExerciseByCategory(category, this.userId)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No exercises found for category: " + category));

        log.info("Found exercises: {}", exercises);

        return ResponseEntity.ok(exercises);
    }
}
