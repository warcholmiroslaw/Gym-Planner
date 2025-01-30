package com.example.demo.services;

import com.example.demo.dtos.WorkoutPlanDto;
import com.example.demo.models.WorkoutPlan;
import com.example.demo.repository.WorkoutPlanRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    private static final Logger log = LoggerFactory.getLogger(WorkoutPlanService.class);


    // return all user workout plans
    public ResponseEntity<Object> getAllWorkoutPlans(Integer userId) {
        Optional<List<WorkoutPlan>> workoutPlan = workoutPlanRepository.findAllWorkoutPlansByUserId(userId);

        if (workoutPlan.isPresent() && !workoutPlan.get().isEmpty()) {
            return ResponseEntity.ok(workoutPlan.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get workout plan by name only if current logged in user is owner of it
    public ResponseEntity<Object> getWorkoutPlanByName(String name, Integer userId) {
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.getWorkoutPlanByName(name, userId);

        if (workoutPlan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(workoutPlan);
    }

    // delete Workout Plan
    public ResponseEntity<Object> deleteWorkoutPlan(Integer workoutPlanId, Integer userId) {
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(workoutPlanId);

        return workoutPlan
                .map(WorkoutPlan::getUserId)
                .map(ownerId -> {
                    if (!ownerId.equals(userId)) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 if user is not owner
                    }
                    workoutPlanRepository.deleteById(workoutPlanId);
                    return ResponseEntity.noContent().build(); // 204 when deleted
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout plan with ID: " + workoutPlanId + " doesn't exist!")); // when WP doesn't exist
    }

    // create Workout plan
    public WorkoutPlan createWorkoutPlan(WorkoutPlanDto workoutPlanDto, Integer userId) {


        WorkoutPlan workoutPlan = new WorkoutPlan()
                .setUserId(userId)
                .setName(workoutPlanDto.getName())
                .setDescription(workoutPlanDto.getDescription())
                .setCreatedAt(LocalDateTime.now());

        return workoutPlanRepository.save(workoutPlan);
    }

    // update Workout plan
    public ResponseEntity<Object> updateWorkoutPlan(WorkoutPlanDto workoutPlanDto, Integer userId) {

        Optional<WorkoutPlan> currentWorkoutPlan = workoutPlanRepository.findById(workoutPlanDto.getId());

        // if workout plan doesn't exist return 404 error
        // also when user is not owner return not found because even though record exist user doesn't have
        // access to read it
        if(currentWorkoutPlan.isEmpty() || !currentWorkoutPlan.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Map new values to currentWorkoutPlan
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(workoutPlanDto, currentWorkoutPlan);

        // change from optional to save updates
        WorkoutPlan existingWorkoutPlan = currentWorkoutPlan.get();

        // Save the updated workout plan
        WorkoutPlan updatedWorkoutPlan = workoutPlanRepository.save(existingWorkoutPlan);

        // Return the updated workout plan with 200 OK
        return ResponseEntity.ok(updatedWorkoutPlan);
    }

}
