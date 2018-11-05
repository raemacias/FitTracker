package models.getCaloriesBurnedForExercises;

import java.util.List;

public class ExerciseBase {

    private List<Exercise> exercises;

    public ExerciseBase() {

    }
    public ExerciseBase(List<Exercise> exercises) {

        this.exercises = exercises;
    }
    public List<Exercise> getExercises() {

        return exercises;
    }
    public void setExercises(List<Exercise> exercises) {

        this.exercises = exercises;
    }
}

