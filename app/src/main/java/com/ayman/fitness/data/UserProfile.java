package com.ayman.fitness.data;

public final class UserProfile {

    public final String name = "Ayman";
    public final int ageYears = 23;
    public final double heightCm = 182.0;
    public final double weightKg = 72.7;
    public final double bodyFatPct = 8.0;
    public final double leanMassKg = weightKg * (1.0 - bodyFatPct / 100.0);
    public final double fatMassKg = weightKg - leanMassKg;
    public final String goal = "Lean bulk — build muscle";

    public int bmr() {
        return (int) Math.round(10 * weightKg + 6.25 * heightCm - 5 * ageYears + 5);
    }

    public int tdee() {
        return (int) Math.round(bmr() * 1.55);
    }

    public int targetKcal() {
        return tdee() + 300;
    }

    public int targetProteinGrams() {
        return (int) Math.round(weightKg * 2.2);
    }

    public int targetFatGrams() {
        return (int) Math.round(weightKg * 0.9);
    }

    public int targetCarbGrams() {
        int kcalFromPF = targetProteinGrams() * 4 + targetFatGrams() * 9;
        return Math.max(0, (targetKcal() - kcalFromPF) / 4);
    }

    public double bmi() {
        double m = heightCm / 100.0;
        return weightKg / (m * m);
    }

    private static UserProfile instance;
    public static UserProfile get() {
        if (instance == null) instance = new UserProfile();
        return instance;
    }
}
