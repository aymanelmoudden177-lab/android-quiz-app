package com.Ayman.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        int    score    = getIntent().getIntExtra("SCORE", 0);
        String category = getIntent().getStringExtra("CATEGORY");

        TextView tvCategory     = findViewById(R.id.tvCategory);
        TextView tvScore        = findViewById(R.id.tvScore);
        TextView tvMedal        = findViewById(R.id.tvMedal);
        TextView tvFeedback     = findViewById(R.id.tvFeedback);
        Button   btnPlayAgain   = findViewById(R.id.btnPlayAgain);
        Button   btnSubmitScore = findViewById(R.id.btnSubmitScore);

        String prize    = getPrize(score);
        String feedback = getGenericFeedback(score);

        tvCategory.setText(category + " Quiz");
        tvScore.setText(score + " / 10");
        tvMedal.setText(prize);
        tvFeedback.setText(feedback);

        // Medal colour
        switch (prize) {
            case "Gold Medal":
                tvMedal.setTextColor(ContextCompat.getColor(this, R.color.gold));
                break;
            case "Silver Medal":
                tvMedal.setTextColor(ContextCompat.getColor(this, R.color.silver));
                break;
            case "Bronze Medal":
                tvMedal.setTextColor(ContextCompat.getColor(this, R.color.bronze));
                break;
            default:
                tvMedal.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
                break;
        }

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnSubmitScore.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("CATEGORY", category);
            intent.putExtra("PRIZE", prize);
            intent.putExtra("FROM_REVIEW", true);
            startActivity(intent);
        });
    }

    // ── Medal thresholds (per brief: Bronze=4+, Silver=8+, Gold=9-10) ────────

    private String getPrize(int score) {
        if (score >= 9) return "Gold Medal";
        if (score >= 8) return "Silver Medal";
        if (score >= 4) return "Bronze Medal";
        return "No Medal";
    }

    // ── Generic feedback (does NOT reveal which questions were wrong) ─────────

    private String getGenericFeedback(int score) {
        if (score == 10) {
            return "Outstanding! A perfect score — you have completely mastered this topic. Exceptional work!";
        } else if (score == 9) {
            return "Excellent performance! You are very close to perfection. Just one small area left to polish.";
        } else if (score == 8) {
            return "Great job! You have a strong grasp of the material. A little more revision will take you all the way.";
        } else if (score >= 6) {
            return "Good effort! You understand most of the core concepts. Revisit the areas where you hesitated to sharpen your knowledge.";
        } else if (score >= 4) {
            return "Not bad — you have a foundation to build on. Spend more time reviewing the key topics and try again.";
        } else if (score >= 2) {
            return "You have some familiarity with the subject, but several important areas need attention. More study is recommended before retaking.";
        } else {
            return "This topic needs more study. Do not be discouraged — review the core concepts thoroughly and give it another go!";
        }
    }
}
