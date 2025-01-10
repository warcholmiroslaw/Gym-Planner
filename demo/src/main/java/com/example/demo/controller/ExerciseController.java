package com.example.demo.controller;

import com.example.demo.services.ExerciseService;
import com.example.demo.models.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        return new ResponseEntity<List<Exercise>>(exerciseService.allExercises(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Exercise>> getExercise(@PathVariable String name) {
        return new ResponseEntity<Optional<Exercise>>(exerciseService.getExerciseByName(name), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<List<Exercise>>> getExerciseByUserId(@PathVariable Integer userId) {
        return new ResponseEntity<Optional<List<Exercise>>>(exerciseService.getExerciseByUserId(userId), HttpStatus.OK);
    }
}
