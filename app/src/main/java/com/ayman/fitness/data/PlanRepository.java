package com.ayman.fitness.data;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public final class PlanRepository {

    private PlanRepository() {}

    public static List<WorkoutDay> workoutWeek() {
        return Arrays.asList(
            new WorkoutDay(
                "Mon", "Upper Power", "Chest, Back, Shoulders — heavy compounds",
                Arrays.asList(
                    new Exercise("Barbell Bench Press", "4 x 5", "2 min rest. Add 2.5 kg when you hit 4x5 clean."),
                    new Exercise("Bent-over Barbell Row", "4 x 5", "Pull to lower chest, brace core."),
                    new Exercise("Standing Overhead Press", "3 x 6", "Squeeze glutes, no leg drive."),
                    new Exercise("Weighted Pull-up", "3 x 6", "Add weight in belt; full hang each rep."),
                    new Exercise("Incline Dumbbell Press", "3 x 8", "30° bench, control eccentric 2s."),
                    new Exercise("Barbell Curl", "3 x 8", "No swinging; strict form."),
                    new Exercise("Lying Triceps Extension", "3 x 10", "Elbows in; full stretch at top.")
                )
            ),
            new WorkoutDay(
                "Tue", "Lower Power", "Quads, Hamstrings, Glutes, Core",
                Arrays.asList(
                    new Exercise("Back Squat", "4 x 5", "3 min rest. Below parallel."),
                    new Exercise("Romanian Deadlift", "3 x 6", "Hinge at hips, neutral spine."),
                    new Exercise("Bulgarian Split Squat", "3 x 8 each leg", "Front shin vertical; quads/glutes."),
                    new Exercise("Lying Leg Curl", "3 x 10", "Squeeze hamstrings 1s at top."),
                    new Exercise("Standing Calf Raise", "4 x 10", "Pause 2s at bottom stretch."),
                    new Exercise("Hanging Leg Raise", "3 x 12", "No swinging; toes to bar.")
                )
            ),
            new WorkoutDay(
                "Wed", "Rest / Mobility", "Active recovery",
                Collections.singletonList(
                    new Exercise("Walk + Stretch", "30–45 min easy walk + 10 min mobility",
                        "Helps recovery. Hit your protein and calories.")
                )
            ),
            new WorkoutDay(
                "Thu", "Upper Hypertrophy", "Volume — chest, back, shoulders, arms",
                Arrays.asList(
                    new Exercise("Incline Barbell Bench", "4 x 8", "60–90s rest."),
                    new Exercise("Lat Pulldown (wide)", "4 x 10", "Pull to upper chest, lean slight back."),
                    new Exercise("Seated Cable Row", "3 x 10", "Squeeze shoulder blades."),
                    new Exercise("Dumbbell Lateral Raise", "4 x 12", "Slight forward lean; pinky up."),
                    new Exercise("Dumbbell Hammer Curl", "3 x 12", "Brachialis focus; slow eccentric."),
                    new Exercise("Cable Triceps Pushdown", "3 x 12", "Elbows pinned; full lockout."),
                    new Exercise("Face Pull", "3 x 15", "External rotation at end range — rear delts.")
                )
            ),
            new WorkoutDay(
                "Fri", "Lower Hypertrophy", "Volume — quads, glutes, hams, calves, core",
                Arrays.asList(
                    new Exercise("Front Squat (or Hack Squat)", "4 x 8", "Upright torso; quads."),
                    new Exercise("Barbell Hip Thrust", "3 x 10", "Squeeze glutes 1s at top."),
                    new Exercise("Leg Press", "3 x 12", "Feet shoulder-width, full ROM."),
                    new Exercise("Walking Lunge", "3 x 12 each leg", "Long stride; knee tracks toes."),
                    new Exercise("Leg Extension", "3 x 15", "Pause 1s at top contraction."),
                    new Exercise("Seated Calf Raise", "4 x 15", "Stretch deep, slow tempo."),
                    new Exercise("Cable Crunch", "3 x 15", "Round spine; engage abs not hips.")
                )
            ),
            new WorkoutDay(
                "Sat", "Rest", "Full recovery",
                Collections.singletonList(
                    new Exercise("Rest", "—",
                        "No training. Eat your full 3000 kcal — gains happen here.")
                )
            ),
            new WorkoutDay(
                "Sun", "Rest / Optional Cardio", "Light cardio if energy allows",
                Collections.singletonList(
                    new Exercise("Optional Zone-2 Cardio", "20–30 min easy bike/walk",
                        "Keep HR ~120–140. Optional — only if you feel fresh.")
                )
            )
        );
    }

    public static List<Meal> dailyMeals() {
        return Arrays.asList(
            new Meal(
                "Breakfast (8:00)",
                "4 whole eggs + 80g oats + 1 banana + 1 tbsp peanut butter + 250 ml milk",
                780, 40, 95, 28
            ),
            new Meal(
                "Lunch (12:30)",
                "200g chicken breast + 250g cooked rice + mixed veggies + 1 tbsp olive oil",
                780, 55, 95, 18
            ),
            new Meal(
                "Pre-workout snack (16:30)",
                "200g Greek yogurt 0% + 50g granola + 1 tbsp honey + 1 apple",
                480, 25, 80, 8
            ),
            new Meal(
                "Post-workout dinner (19:30)",
                "200g lean beef mince OR salmon + 300g sweet potato + green veggies",
                700, 45, 70, 22
            ),
            new Meal(
                "Evening snack (22:00)",
                "30g whey + 250ml milk + 30g almonds (slow-release casein bonus)",
                430, 35, 20, 22
            )
        );
    }

    public static String[] supplements() {
        return new String[] {
            "Whey protein — 1 scoop post-workout (only if you miss your protein target)",
            "Creatine monohydrate — 5g/day, every day, any time",
            "Vitamin D3 — 2000 IU/day with a fat-containing meal",
            "Omega-3 fish oil — 1–2g EPA+DHA/day",
            "Caffeine 200mg pre-workout (optional, not within 6h of bedtime)"
        };
    }

    public static int todayWorkoutIndex() {
        Calendar c = Calendar.getInstance();
        int dow = c.get(Calendar.DAY_OF_WEEK);
        switch (dow) {
            case Calendar.MONDAY:    return 0;
            case Calendar.TUESDAY:   return 1;
            case Calendar.WEDNESDAY: return 2;
            case Calendar.THURSDAY:  return 3;
            case Calendar.FRIDAY:    return 4;
            case Calendar.SATURDAY:  return 5;
            default:                 return 6;
        }
    }
}
