package com.example.demo.controller;

import com.example.demo.dtos.WPSetsDto;
import com.example.demo.services.JwtService;
import com.example.demo.services.SetsService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/workoutplan/{workoutPlanId}/exercise/{wpeId}/sets")
public class WPSetsController {

    @Autowired
    SetsService setsService;


    private Integer workoutPlanId;
    private Integer wpeId;
    private Integer userId;

    Logger log = LoggerFactory.getLogger(WPSetsController.class);

    final JwtService jwtService;

    public WPSetsController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @ModelAttribute
    public void setVariables(
            @PathVariable("workoutPlanId") Integer workoutPlanId,
            @PathVariable("wpeId") Integer wpeId,
            HttpServletRequest request)
    {
        this.workoutPlanId = workoutPlanId;
        this.wpeId = wpeId;
        this.userId = jwtService.extractUserId(request);

    }


    // add sets to exercise in workout plan
    @PostMapping
    public ResponseEntity<Object> createWorkoutPlanExerciseSets(
            @RequestBody WPSetsDto setsDto) {
        log.info("Try to create workout plan sets from existing workout plan");

        return setsService.addSets(setsDto, this.wpeId, this.workoutPlanId, this.userId);
    }

    @DeleteMapping("/{setNumber}")
    public ResponseEntity<Object> deleteWorkoutPlanExerciseSets(
            @PathVariable Integer setNumber
            ){

        log.info("Try to delete workout plan sets from existing workout plan");

        return setsService.deleteSet(setNumber, this.wpeId, this.workoutPlanId, this.userId);
    }



}
