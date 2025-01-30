package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "workout_plans")
@Data
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="workout_plan_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id", referencedColumnName = "user_id", nullable = true,
            insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    private Integer userId;

    private String name;
    private String description;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
}
