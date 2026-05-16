package com.ayman.fitness.data;

public final class Meal {
    public final String name;
    public final String foods;
    public final int kcal;
    public final int proteinG;
    public final int carbsG;
    public final int fatG;

    public Meal(String name, String foods, int kcal, int proteinG, int carbsG, int fatG) {
        this.name = name;
        this.foods = foods;
        this.kcal = kcal;
        this.proteinG = proteinG;
        this.carbsG = carbsG;
        this.fatG = fatG;
    }
}
