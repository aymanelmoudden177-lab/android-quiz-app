package com.ayman.fitness.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ayman.fitness.R;
import com.ayman.fitness.data.PlanRepository;
import com.ayman.fitness.data.UserProfile;
import com.ayman.fitness.data.WorkoutDay;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserProfile p = UserProfile.get();

        TextView greeting = findViewById(R.id.tv_greeting);
        greeting.setText("Hey " + p.name + " 👊");

        TextView stats = findViewById(R.id.tv_stats);
        stats.setText(String.format(
            "%.1f kg · %.0f cm · %.1f%% BF · BMI %.1f",
            p.weightKg, p.heightCm, p.bodyFatPct, p.bmi()
        ));

        TextView kcal = findViewById(R.id.tv_kcal_target);
        kcal.setText(p.targetKcal() + " kcal");

        TextView macros = findViewById(R.id.tv_macros);
        macros.setText(String.format(
            "P %dg  ·  C %dg  ·  F %dg",
            p.targetProteinGrams(), p.targetCarbGrams(), p.targetFatGrams()
        ));

        WorkoutDay today = PlanRepository.workoutWeek().get(PlanRepository.todayWorkoutIndex());
        TextView todayTitle = findViewById(R.id.tv_today_title);
        todayTitle.setText(today.dayLabel + " — " + today.title);
        TextView todayFocus = findViewById(R.id.tv_today_focus);
        todayFocus.setText(today.focus);

        findViewById(R.id.card_today).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WorkoutDetailActivity.class);
                i.putExtra(WorkoutDetailActivity.EXTRA_DAY_INDEX,
                        PlanRepository.todayWorkoutIndex());
                startActivity(i);
            }
        });

        findViewById(R.id.btn_workout).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WorkoutListActivity.class));
            }
        });
        findViewById(R.id.btn_nutrition).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NutritionActivity.class));
            }
        });
        findViewById(R.id.btn_profile).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }
}
