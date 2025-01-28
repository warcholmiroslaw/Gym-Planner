package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workout_plans")
@Data
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // object will be loaded only when needed
    @JoinColumn(name ="user_id", referencedColumnName = "user_id", nullable = true,
            insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Integer userId;

    private String name;
    private String description;

    @Column(name = "created_at")
    private java.time.LocalDate CreatedAt;
}
