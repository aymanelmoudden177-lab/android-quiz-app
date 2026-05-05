package com.Ayman.quizapp;

import java.io.Serializable;

public class Question implements Serializable {

    private final String questionCategory;
    private final String questionAsked;
    private final String choiceA;
    private final String choiceB;
    private final String choiceC;
    private final String choiceD;
    private final String correctChoice;
    private final String feedback;

    public Question(String questionCategory, String questionAsked,
                    String choiceA, String choiceB,
                    String choiceC, String choiceD,
                    String correctChoice, String feedback) {
        this.questionCategory = questionCategory;
        this.questionAsked    = questionAsked;
        this.choiceA          = choiceA;
        this.choiceB          = choiceB;
        this.choiceC          = choiceC;
        this.choiceD          = choiceD;
        this.correctChoice    = correctChoice;
        this.feedback         = feedback;
    }

    public String getQuestionCategory() { return questionCategory; }
    public String getQuestionAsked()    { return questionAsked; }
    public String getChoiceA()          { return choiceA; }
    public String getChoiceB()          { return choiceB; }
    public String getChoiceC()          { return choiceC; }
    public String getChoiceD()          { return choiceD; }
    public String getCorrectChoice()    { return correctChoice; }
    public String getFeedback()         { return feedback; }
}
