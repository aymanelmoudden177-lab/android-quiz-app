package com.ayman.fitness.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.fitness.R;
import com.ayman.fitness.data.Exercise;
import com.ayman.fitness.data.PlanRepository;
import com.ayman.fitness.data.WorkoutDay;

import java.util.List;

public class WorkoutDetailActivity extends AppCompatActivity {

    public static final String EXTRA_DAY_INDEX = "day_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        int idx = getIntent().getIntExtra(EXTRA_DAY_INDEX, 0);
        List<WorkoutDay> week = PlanRepository.workoutWeek();
        if (idx < 0 || idx >= week.size()) idx = 0;
        WorkoutDay day = week.get(idx);

        TextView header = findViewById(R.id.tv_header);
        header.setText(day.dayLabel + " — " + day.title);

        TextView focus = findViewById(R.id.tv_focus);
        focus.setText(day.focus);

        RecyclerView rv = findViewById(R.id.rv_exercises);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ExerciseAdapter(day.exercises));
    }

    static class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder> {
        private final List<Exercise> items;
        ExerciseAdapter(List<Exercise> items) { this.items = items; }

        @NonNull @Override
        public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_exercise, parent, false);
            return new ExerciseHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ExerciseHolder h, int position) {
            Exercise e = items.get(position);
            h.name.setText(e.name);
            h.sets.setText(e.setsReps);
            h.tip.setText(e.tip);
        }

        @Override public int getItemCount() { return items.size(); }
    }

    static class ExerciseHolder extends RecyclerView.ViewHolder {
        TextView name, sets, tip;
        ExerciseHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tv_ex_name);
            sets = v.findViewById(R.id.tv_ex_sets);
            tip  = v.findViewById(R.id.tv_ex_tip);
        }
    }
}
