package com.Ayman.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "QuizScores";
    private static final String SCORES_KEY = "scores";
    private static final int    MAX_SCORES = 10;

    private LinearLayout          scoresContainer;
    private ArrayList<ScoreEntry> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Top 10 Scores");
        }

        scoresContainer = findViewById(R.id.scoresContainer);
        Button btnBack        = findViewById(R.id.btnBack);
        Button btnClearScores = findViewById(R.id.btnClearScores);

        scoreList = loadScores();

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnClearScores.setOnClickListener(v ->
            new AlertDialog.Builder(this)
                .setTitle("Clear All Scores")
                .setMessage("Are you sure you want to delete all scores?")
                .setPositiveButton("Yes", (d, w) -> {
                    scoreList.clear();
                    saveScores();
                    displayScores();
                })
                .setNegativeButton("No", null)
                .show()
        );

        // Coming from ReviewActivity — offer to save the new score
        if (getIntent().getBooleanExtra("FROM_REVIEW", false)) {
            int    score    = getIntent().getIntExtra("SCORE", 0);
            String category = getIntent().getStringExtra("CATEGORY");
            String prize    = getIntent().getStringExtra("PRIZE");
            showNameInputDialog(score, category, prize);
        }

        displayScores();
    }

    // ── Name input dialog ─────────────────────────────────────────────────────

    private void showNameInputDialog(int score, String category, String prize) {
        EditText nameInput = new EditText(this);
        nameInput.setHint("Enter your name");
        nameInput.setSingleLine(true);

        new AlertDialog.Builder(this)
            .setTitle("Submit Your Score")
            .setMessage("Add your score to the leaderboard?\n\n"
                    + category + "  |  " + score + "/10  |  " + prize)
            .setView(nameInput)
            .setPositiveButton("Submit", (dialog, which) -> {
                String name = nameInput.getText().toString().trim();
                if (name.isEmpty()) name = "???";
                if (name.length() > 12) name = name.substring(0, 12);

                scoreList.add(new ScoreEntry(name, score, category, prize));
                Collections.sort(scoreList, (a, b) -> b.getScore() - a.getScore());
                while (scoreList.size() > MAX_SCORES) {
                    scoreList.remove(scoreList.size() - 1);
                }
                saveScores();
                displayScores();
            })
            .setNegativeButton("Cancel", null)
            .show();
    }

    // ── Leaderboard display ───────────────────────────────────────────────────

    private void displayScores() {
        scoresContainer.removeAllViews();

        if (scoreList.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("No scores yet. Complete a quiz to appear here!");
            empty.setPadding(32, 48, 32, 48);
            empty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            empty.setTextSize(15f);
            scoresContainer.addView(empty);
            return;
        }

        for (int i = 0; i < scoreList.size(); i++) {
            ScoreEntry entry    = scoreList.get(i);
            View       itemView = getLayoutInflater().inflate(R.layout.score_item, scoresContainer, false);

            TextView tvRank     = itemView.findViewById(R.id.tvRank);
            TextView tvName     = itemView.findViewById(R.id.tvName);
            TextView tvScore    = itemView.findViewById(R.id.tvScore);
            TextView tvCategory = itemView.findViewById(R.id.tvCategory);
            TextView tvPrize    = itemView.findViewById(R.id.tvPrize);

            tvRank.setText(String.valueOf(i + 1));
            tvName.setText(entry.getName());
            tvScore.setText(entry.getScore() + "/10");

            String cat = "Software Engineering".equals(entry.getCategory()) ? "Soft. Eng." : "Data Struct.";
            tvCategory.setText(cat);
            tvPrize.setText(entry.getPrize());

            // Gold / silver / bronze row highlights
            if (i == 0) {
                itemView.setBackgroundColor(ContextCompat.getColor(this, R.color.rank_gold));
            } else if (i == 1) {
                itemView.setBackgroundColor(ContextCompat.getColor(this, R.color.rank_silver));
            } else if (i == 2) {
                itemView.setBackgroundColor(ContextCompat.getColor(this, R.color.rank_bronze));
            }

            scoresContainer.addView(itemView);
        }
    }

    // ── SharedPreferences persistence ─────────────────────────────────────────

    private ArrayList<ScoreEntry> loadScores() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(SCORES_KEY, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<ArrayList<ScoreEntry>>() {}.getType();
        ArrayList<ScoreEntry> list = new Gson().fromJson(json, type);
        return list != null ? list : new ArrayList<>();
    }

    private void saveScores() {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            .edit()
            .putString(SCORES_KEY, new Gson().toJson(scoreList))
            .apply();
    }
}
