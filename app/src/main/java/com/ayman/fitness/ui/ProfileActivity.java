package com.ayman.fitness.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ayman.fitness.R;
import com.ayman.fitness.data.UserProfile;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserProfile p = UserProfile.get();

        ((TextView) findViewById(R.id.tv_name)).setText(p.name);
        ((TextView) findViewById(R.id.tv_age)).setText(p.ageYears + " years");
        ((TextView) findViewById(R.id.tv_height)).setText(String.format("%.0f cm (5'11.6\")", p.heightCm));
        ((TextView) findViewById(R.id.tv_weight)).setText(String.format("%.1f kg", p.weightKg));
        ((TextView) findViewById(R.id.tv_bmi)).setText(String.format("%.1f", p.bmi()));
        ((TextView) findViewById(R.id.tv_bf)).setText(String.format("%.1f%%", p.bodyFatPct));
        ((TextView) findViewById(R.id.tv_lbm)).setText(String.format("%.1f kg", p.leanMassKg));
        ((TextView) findViewById(R.id.tv_fm)).setText(String.format("%.1f kg", p.fatMassKg));
        ((TextView) findViewById(R.id.tv_bmr)).setText(p.bmr() + " kcal");
        ((TextView) findViewById(R.id.tv_tdee)).setText(p.tdee() + " kcal");
        ((TextView) findViewById(R.id.tv_target)).setText(p.targetKcal() + " kcal");
        ((TextView) findViewById(R.id.tv_goal)).setText(p.goal);

        ((TextView) findViewById(R.id.tv_advice)).setText(
            "You're at 8% BF and 1.82m / 72.7 kg — perfect window for a clean lean bulk.\n\n" +
            "Aim for +0.25–0.5 kg per week. If weekly weigh-ins jump >0.6 kg, drop carbs by ~30g.\n\n" +
            "Train hard, log every set, sleep 8h, and re-take this body-fat scan every 8 weeks."
        );
    }
}
