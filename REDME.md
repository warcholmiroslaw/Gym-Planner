# Gym Planner

Gym Planner is an application that allows users to create, manage, and follow workout plans. Users can add their custom training plans and exercises, or choose from a collection of global workout routines. This tool helps individuals stay on track with their fitness goals and provides a structured way to plan and track their workouts.

## Features

- Create personalized workout plans.
- Add custom exercises to your workout routine.
- Use pre-built global workout plans for various fitness levels.
- Track and manage your workout history.
- User-friendly interface for easy navigation.

## Technologies Used

- **Spring Boot** – for backend development.
- **Spring Security** – for authentication and authorization.
- **JWT (JSON Web Tokens)** – for secure user authentication.
- **Swagger** – for API documentation.
- **PostgreSQL** – for storing user data and workout plans.

## Database Structure

The Gym Planner application uses a relational database to store user data, workout plans, exercises, and training sessions. Below is an overview of the database schema:

### 1. **Users**
This table stores information about users of the application.

- **user_id** (SERIAL): Unique identifier for each user.
- **email** (VARCHAR(255)): User's email address (unique).
- **name** (VARCHAR(255)): User's first name.
- **surname** (VARCHAR(255)): User's last name.
- **password** (VARCHAR(255)): Encrypted password for the user.
- **created_at** (TIMESTAMP): Date and time when the user was created.

```sql
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

<br/>

### 2 **Muscle Groups**
This table stores information about muscle groups, which are used to categorize exercises. This table  helps in organizing exercises based on the muscle group they target (e.g., Chest, Legs, Back).

- **muscle_group_id** (SERIAL): Unique identifier for each muscle group.
- **name** (VARCHAR(255)): Name of the muscle group (e.g., Chest, Legs, Arms, etc.).

```sql
CREATE TABLE muscle_groups (
    muscle_group_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

<br/>


### 3. **Exercises**
The **Exercises** table stores information about individual exercises that users can perform. Exercises can be either user-created or global exercises. Each exercise may be associated with a specific muscle group and a user (if it's created by a user). This table helps to organize exercises and enables users to customize their workout plans.

- **exercise_id** (SERIAL): Unique identifier for each exercise.
- **user_id** (INT): Foreign key referencing the `users` table. This represents the user who created the exercise. It is nullable for global exercises (i.e., exercises that are not created by any particular user).
- **muscle_group_id** (INT): Foreign key referencing the `muscle_groups` table. Specifies which muscle group the exercise targets (nullable if the exercise is not associated with any specific muscle group).
- **name** (VARCHAR(255)): The name of the exercise (e.g., "Push-up", "Squat", "Deadlift").
- **description** (TEXT): A detailed description of the exercise, which could include instructions on how to perform it correctly.

```sql
CREATE TABLE exercises (
    exercise_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE SET NULL,
    muscle_group_id INT REFERENCES muscle_groups(muscle_group_id) ON DELETE SET NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

```
<br/>

### 4. **Workout Plans**
The **Workout Plans** table stores custom workout plans created by users. Each workout plan can include a variety of exercises, and users can design their routines based on their fitness goals. This table allows users to organize their workouts and keep track of different plans they've created.

- **workout_plan_id** (SERIAL): Unique identifier for each workout plan.
- **user_id** (INT): Foreign key referencing the `users` table. Specifies the user who created the workout plan. This field ensures that each workout plan is associated with a specific user.
- **name** (VARCHAR(255)): The name of the workout plan (e.g., "Strength Training", "Full Body Routine").
- **description** (TEXT): A detailed description of the workout plan, explaining its purpose, structure, or any other relevant information.
- **created_at** (TIMESTAMP): Timestamp indicating when the workout plan was created. This helps track when the plan was added.

```sql
CREATE TABLE workout_plans (
    workout_plan_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```
<br/>

### 5. **Workout Plan Exercises**
The **Workout Plan Exercises** table establishes a relationship between workout plans and exercises. This table links specific exercises to their corresponding workout plans, enabling users to organize which exercises should be included in each workout plan. 

- **wpe_id** (SERIAL): Unique identifier for each workout plan-exercise association.
- **workout_plan_id** (INT): Foreign key referencing the `workout_plans` table. Specifies which workout plan the exercise is part of.
- **exercise_id** (INT): Foreign key referencing the `exercises` table. Specifies which exercise is part of the workout plan.

This table allows for many-to-many relationships between workout plans and exercises. A workout plan can include multiple exercises, and an exercise can be part of multiple workout plans.

```sql
CREATE TABLE workout_plan_exercises (
    wpe_id SERIAL PRIMARY KEY,
    workout_plan_id INT NOT NULL REFERENCES workout_plans(workout_plan_id) ON DELETE CASCADE,
    exercise_id INT NOT NULL REFERENCES exercises(exercise_id) ON DELETE CASCADE
);
```

### 5. **Workout Plan Sets**
The **Workout Plan Sets** table defines the specific sets, repetitions, and weights for each exercise within a workout plan. This table allows users to define the structure of each exercise by specifying the number of sets, repetitions, and the weight used for each set. 

- **wps_id** (SERIAL): Unique identifier for each set within a workout plan.
- **wpe_id** (INT): Foreign key referencing the `workout_plan_exercises` table. Specifies the exercise that the set is associated with.
- **set_number** (INT): The number of the set within the exercise (e.g., Set 1, Set 2, etc.).
- **reps** (INT): The number of repetitions (reps) to be performed in the set.
- **weight** (DECIMAL(10,2)): The weight to be lifted for the set, recorded with a precision of two decimal places (e.g., 25.00 kg).

This table helps users define the exact details of each exercise in a workout plan, ensuring clarity on how the exercise should be performed in terms of sets, reps, and weight.

```sql
CREATE TABLE workout_plan_sets (
    wps_id SERIAL PRIMARY KEY,
    wpe_id INT NOT NULL REFERENCES workout_plan_exercises(wpe_id) ON DELETE CASCADE,
    set_number INT NOT NULL,
    reps INT NOT NULL,
    weight DECIMAL(10,2) NOT NULL
);
```
<br/>


### 6. **Training Sessions**
The **Training Sessions** table stores logs of individual training sessions performed by users. Each session is linked to a specific user and, optionally, a workout plan. This table allows users to track their workout sessions, including the start and end times, the associated workout plan, and any notes about the session.

- **training_session_id** (SERIAL): Unique identifier for each training session.
- **user_id** (INT): Foreign key referencing the `users` table. Specifies which user performed the session.
- **workout_plan_id** (INT): Foreign key referencing the `workout_plans` table. Optionally links the training session to a specific workout plan. If no workout plan was used, this field can be `NULL`.
- **date** (DATE): The date of the training session. Default is the current date when the session is logged.
- **start_time** (TIMESTAMP): The timestamp of when the training session started.
- **end_time** (TIMESTAMP): The timestamp of when the training session ended. This field can be `NULL` if the session is still ongoing.
- **notes** (TEXT): Optional field to store additional information or notes about the training session (e.g., performance, how the user felt, any adjustments made during the session).

```sql
CREATE TABLE training_sessions (
    training_session_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    workout_plan_id INT REFERENCES workout_plans(workout_plan_id) ON DELETE SET NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    start_time TIMESTAMP NOT NULL DEFAULT NOW(),
    end_time TIMESTAMP,
    notes TEXT
);
```
<br/>


### 7. **Training Session Exercises**
The **Training Session Exercises** table records the specific exercises performed during a training session. Each entry links a particular exercise to a training session, enabling users to track which exercises they performed on a given day. This table provides detailed insight into the structure of each training session, especially for exercises within the session.

- **tse_id** (SERIAL): Unique identifier for each record in the training session exercises table.
- **training_session_id** (INT): Foreign key referencing the `training_sessions` table. Specifies which training session the exercise is part of.
- **exercise_id** (INT): Foreign key referencing the `exercises` table. Specifies which exercise was performed during the session.

This table facilitates the tracking of individual exercises performed within a specific training session, ensuring that users can see a full record of their workouts, including which exercises were part of each session.

```sql
CREATE TABLE training_session_exercises (
    tse_id SERIAL PRIMARY KEY,
    training_session_id INT NOT NULL REFERENCES training_sessions(training_session_id) ON DELETE CASCADE,
    exercise_id INT NOT NULL REFERENCES exercises(exercise_id) ON DELETE CASCADE
);
```
<br/>


### 8. **Training Session Sets**
The **Training Session Sets** table logs the specific sets, repetitions, and weights used during exercises performed in a training session. Each entry represents a set performed for a specific exercise within the session, providing detailed tracking of the user's workout performance.

- **tss_id** (SERIAL): Unique identifier for each training session set.
- **tse_id** (INT): Foreign key referencing the `training_session_exercises` table. Specifies which exercise set this record is associated with.
- **set_number** (INT): The number of the set within the exercise (e.g., Set 1, Set 2, etc.).
- **reps** (INT): The number of repetitions (reps) performed in the set.
- **weight** (DECIMAL(10,2)): The weight used during the set, recorded with a precision of two decimal places (e.g., 25.00 kg).

This table allows users to track the exact performance for each set within their training sessions, including the number of repetitions completed and the weight lifted, helping them monitor their progress over time.

```sql
CREATE TABLE training_session_sets (
    tss_id SERIAL PRIMARY KEY,
    tse_id INT NOT NULL REFERENCES training_session_exercises(tse_id) ON DELETE CASCADE,
    set_number INT NOT NULL,
    reps INT NOT NULL,
    weight DECIMAL(10,2) NOT NULL
);
```

## ERD Diagram


The ERD illustrates the relationships between the various entities in the Gym Planner system. It shows how users interact with exercises, workout plans, and training sessions, and how these entities are connected to track and organize workout data.

- **Users** can create **Workout Plans** and **Exercises**.
- **Workout Plans** contain multiple **Exercises** (many-to-many relationship).
- Each **Exercise** is associated with a **Muscle Group**.
- **Workout Plans** define specific **Sets** (reps, weight, etc.) for each **Exercise**.
- **Training Sessions** log each workout performed by a **User**, including the specific **Exercises** performed, the **Sets** completed, and the **weight/reps** used.
- **Training Session Exercises** links **Exercises** to **Training Sessions**, while **Training Session Sets** tracks the details of the **Sets** performed.

The diagram provides a visual representation of how each table is related and how data flows through the system, helping to better understand the structure of the Gym Planner application.

<div style="text-align: center;">
  <img src="/images/diagram_ERD.png" style="width: 100%; height: auto;" />
</div>

<h1>Sign up</h1>

<div style="text-align: center;">
  <img src="/images/signup.png" style="width: 100%; height: auto;" />
</div>

<h1>Login</h1>

<div style="text-align: center;">
  <img src="/images/login.png" style="width: 100%; height: auto;" />
</div>


<h1>Dashboard</h1>

<div style="text-align: center;">
  <img src="/images/dashboard.png" style="width: 100%; height: auto;" />
</div>


<h1>Exercise Category</h1>

<div style="text-align: center;">
  <img src="/images/exercise_category.png" style="width: 100%; height: auto;" />
</div>



