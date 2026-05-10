package com.ayman.fitness.ui;

import android.content.Intent;
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
import com.ayman.fitness.data.PlanRepository;
import com.ayman.fitness.data.WorkoutDay;

import java.util.List;

public class WorkoutListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        RecyclerView rv = findViewById(R.id.rv_days);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new DayAdapter(PlanRepository.workoutWeek()));
    }

    private class DayAdapter extends RecyclerView.Adapter<DayHolder> {
        private final List<WorkoutDay> days;
        DayAdapter(List<WorkoutDay> days) { this.days = days; }

        @NonNull @Override
        public DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_workout_day, parent, false);
            return new DayHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull DayHolder h, int position) {
            final WorkoutDay d = days.get(position);
            h.day.setText(d.dayLabel);
            h.title.setText(d.title);
            h.focus.setText(d.focus);
            final int idx = position;
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(WorkoutListActivity.this,
                            WorkoutDetailActivity.class);
                    i.putExtra(WorkoutDetailActivity.EXTRA_DAY_INDEX, idx);
                    startActivity(i);
                }
            });
        }

        @Override public int getItemCount() { return days.size(); }
    }

    static class DayHolder extends RecyclerView.ViewHolder {
        TextView day, title, focus;
        DayHolder(View v) {
            super(v);
            day = v.findViewById(R.id.tv_day);
            title = v.findViewById(R.id.tv_title);
            focus = v.findViewById(R.id.tv_focus);
        }
    }
}
