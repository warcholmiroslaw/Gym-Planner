package com.example.demo.services;


import com.example.demo.controller.WorkoutPlanController;
import com.example.demo.dtos.WPSetsDto;
import com.example.demo.models.WorkoutPlanSets;
import com.example.demo.repository.SetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Sets;

@Service
public class SetsService {

    @Autowired
    private SetsRepository setsRepository;


    private static final Logger log = LoggerFactory.getLogger(SetsService.class);


    // to check if set already exists for specific
    // exercise in working plan you have to count
    // wpe_id in workout_plan_sets

    // add sets
    public ResponseEntity<Object> addSets(WPSetsDto set,Integer wpeId, Integer planId, Integer userId) {
        Integer countSets = this.numberOfSetsExists(wpeId, planId, userId);

        log.info("Number of sets found: " + countSets);

        WorkoutPlanSets workoutPlanSets = new WorkoutPlanSets()
                .setWorkoutPlanExerciseId(wpeId)
                .setSetNumber(countSets + 1) // increase number of set
                .setReps(set.getReps())
                .setWeight(set.getWeight());

        return new ResponseEntity<>(setsRepository.save(workoutPlanSets), HttpStatus.CREATED);
    }

    private Integer numberOfSetsExists(Integer wpeId, Integer planId, Integer userId) {
        return setsRepository.countWorkoutPlanSetsByExerciseAndPlan(wpeId, planId, userId);
    }


    public ResponseEntity<Object> deleteSet(Integer setNumber, Integer wpeId, Integer planId, Integer userId){
    Integer countSets = this.numberOfSetsExists(wpeId, planId, userId);

    if (countSets == 0 || countSets < setNumber) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    log.info("delete row number : " + setNumber);
    setsRepository.deleteByIds(setNumber, wpeId, planId, userId);

    log.info("Row deleted : " + setNumber);
    return ResponseEntity.noContent().build(); // 204 when deleted
    }
}
