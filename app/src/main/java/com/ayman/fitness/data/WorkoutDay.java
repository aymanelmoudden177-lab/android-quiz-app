package com.ayman.fitness.data;

import java.util.List;

public final class WorkoutDay {
    public final String dayLabel;
    public final String title;
    public final String focus;
    public final List<Exercise> exercises;

    public WorkoutDay(String dayLabel, String title, String focus, List<Exercise> exercises) {
        this.dayLabel = dayLabel;
        this.title = title;
        this.focus = focus;
        this.exercises = exercises;
    }
}
