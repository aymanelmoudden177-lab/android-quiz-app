package com.Ayman.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "QUIZ_CATEGORY";
    public static final String EXTRA_MODE     = "QUIZ_MODE";
    public static final String MODE_NORMAL    = "NORMAL";
    public static final String MODE_LEARNING  = "LEARNING";

    private RadioGroup modeRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modeRadioGroup = findViewById(R.id.radioGroupMode);

        Button btnSoftwareEngineering = findViewById(R.id.btnSoftwareEngineering);
        Button btnDataStructures      = findViewById(R.id.btnDataStructures);
        Button btnViewScores          = findViewById(R.id.btnViewScores);

        btnSoftwareEngineering.setOnClickListener(v -> startQuiz("Software Engineering"));
        btnDataStructures.setOnClickListener(v -> startQuiz("Data Structures"));

        btnViewScores.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, ScoreActivity.class))
        );
    }

    private void startQuiz(String category) {
        String mode = (modeRadioGroup.getCheckedRadioButtonId() == R.id.radioLearningMode)
                ? MODE_LEARNING : MODE_NORMAL;

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_MODE, mode);
        startActivity(intent);
    }
}
