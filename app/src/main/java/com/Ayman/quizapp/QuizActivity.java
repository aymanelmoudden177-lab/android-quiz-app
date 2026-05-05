package com.Ayman.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private static final int TOTAL_QUESTIONS = 10;

    // UI
    private TextView    tvQuestionNumber;
    private TextView    tvQuestionText;
    private RadioGroup  radioGroupAnswers;
    private RadioButton radioA, radioB, radioC, radioD;
    private Button      btnNext;
    private CardView    cardFeedback;
    private TextView    tvFeedback;
    private ProgressBar progressBar;

    // State
    private ArrayList<Question> quizList;
    private Question currentQuestion;
    private String   category;
    private String   mode;
    private int      score           = 0;
    private int      questionNumber  = 0;
    private boolean  answerSubmitted = false;

    // ── Lifecycle ─────────────────────────────────────────────────────────────

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        category = getIntent().getStringExtra(MainActivity.EXTRA_CATEGORY);
        mode     = getIntent().getStringExtra(MainActivity.EXTRA_MODE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(category + " Quiz");
        }

        initViews();
        loadQuizData();
        loadNextQuestion();
    }

    // ── Setup ─────────────────────────────────────────────────────────────────

    private void initViews() {
        tvQuestionNumber  = findViewById(R.id.tvQuestionNumber);
        tvQuestionText    = findViewById(R.id.tvQuestionText);
        radioGroupAnswers = findViewById(R.id.radioGroupAnswers);
        radioA            = findViewById(R.id.radioA);
        radioB            = findViewById(R.id.radioB);
        radioC            = findViewById(R.id.radioC);
        radioD            = findViewById(R.id.radioD);
        btnNext           = findViewById(R.id.btnNext);
        cardFeedback      = findViewById(R.id.cardFeedback);
        tvFeedback        = findViewById(R.id.tvFeedback);
        progressBar       = findViewById(R.id.progressBar);

        btnNext.setOnClickListener(v -> handleNextButton());
    }

    private void loadQuizData() {
        if ("Software Engineering".equals(category)) {
            quizList = QuizData.getSoftwareEngineeringQuiz();
        } else {
            quizList = QuizData.getDataStructureQuiz();
        }
    }

    // ── Question display ──────────────────────────────────────────────────────

    private void loadNextQuestion() {
        cardFeedback.setVisibility(View.GONE);
        radioGroupAnswers.clearCheck();
        answerSubmitted = false;

        currentQuestion = getRandomQuestion(quizList);
        if (currentQuestion == null) return;

        questionNumber++;
        progressBar.setProgress(questionNumber);
        tvQuestionNumber.setText("Question " + questionNumber + " of " + TOTAL_QUESTIONS);
        tvQuestionText.setText(currentQuestion.getQuestionAsked());

        radioA.setText("A)  " + currentQuestion.getChoiceA());
        radioB.setText("B)  " + currentQuestion.getChoiceB());
        radioC.setText("C)  " + currentQuestion.getChoiceC());
        radioD.setText("D)  " + currentQuestion.getChoiceD());

        btnNext.setText(questionNumber == TOTAL_QUESTIONS ? "I am ready to submit my answers" : "Next Question");
    }

    // ── Button logic ──────────────────────────────────────────────────────────

    private void handleNextButton() {
        int selectedId = radioGroupAnswers.getCheckedRadioButtonId();

        if (selectedId == -1) {
            new AlertDialog.Builder(this)
                    .setTitle("No Answer Selected")
                    .setMessage("Please select an answer before continuing.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        // Learning Mode: first tap shows feedback; second tap advances
        if (MainActivity.MODE_LEARNING.equals(mode) && !answerSubmitted) {
            checkAnswerLearningMode(selectedId);
            return;
        }

        // Normal Mode or second tap in Learning Mode
        if (!answerSubmitted) {
            checkAnswerNormal(selectedId);
        }

        if (questionNumber >= TOTAL_QUESTIONS) {
            showSubmitConfirmation();
        } else {
            loadNextQuestion();
        }
    }

    private void checkAnswerNormal(int selectedId) {
        if (getSelectedLetter(selectedId).equals(currentQuestion.getCorrectChoice())) {
            score++;
        }
        answerSubmitted = true;
    }

    private void checkAnswerLearningMode(int selectedId) {
        boolean correct = getSelectedLetter(selectedId).equals(currentQuestion.getCorrectChoice());
        if (correct) score++;
        answerSubmitted = true;

        cardFeedback.setVisibility(View.VISIBLE);
        if (correct) {
            cardFeedback.setCardBackgroundColor(ContextCompat.getColor(this, R.color.feedback_correct));
            tvFeedback.setText("Correct!  " + currentQuestion.getFeedback());
        } else {
            cardFeedback.setCardBackgroundColor(ContextCompat.getColor(this, R.color.feedback_incorrect));
            tvFeedback.setText("Incorrect.  " + currentQuestion.getFeedback());
        }

        btnNext.setText(questionNumber >= TOTAL_QUESTIONS ? "I am ready to submit my answers" : "Next Question");
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private String getSelectedLetter(int selectedId) {
        if (selectedId == R.id.radioA) return "A";
        if (selectedId == R.id.radioB) return "B";
        if (selectedId == R.id.radioC) return "C";
        if (selectedId == R.id.radioD) return "D";
        return "";
    }

    private void showSubmitConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Submit Quiz")
                .setMessage("Are you sure you want to submit your answers?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent(QuizActivity.this, ReviewActivity.class);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("CATEGORY", category);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("No", null)
                .show();
    }

    /**
     * Returns a random question from the list, removing it (as specified in the brief).
     */
    private Question getRandomQuestion(ArrayList<Question> quizArrayList) {
        if (quizArrayList.size() == 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * quizArrayList.size());
        Question q = quizArrayList.get(randomIndex);
        quizArrayList.remove(randomIndex);
        return q;
    }
}
