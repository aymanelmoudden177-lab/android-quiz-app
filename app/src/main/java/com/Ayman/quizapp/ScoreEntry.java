package com.Ayman.quizapp;

public class ScoreEntry {

    private String name;
    private int    score;
    private String category;
    private String prize;

    public ScoreEntry(String name, int score, String category, String prize) {
        this.name     = name;
        this.score    = score;
        this.category = category;
        this.prize    = prize;
    }

    public String getName()     { return name; }
    public int    getScore()    { return score; }
    public String getCategory() { return category; }
    public String getPrize()    { return prize; }
}
