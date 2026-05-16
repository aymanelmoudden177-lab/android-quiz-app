# Ayman Fitness

A personal Android app — a 4-day workout split and a daily nutrition plan tailored
to Ayman's stats (23 y/o male, 1.82 m, 72.7 kg, 8% body fat).

## What it does

- **Dashboard** — today's workout + daily kcal/macros at a glance.
- **My Workout** — full week view with a 4-day Upper/Lower (Power + Hypertrophy)
  split, plus rest-day guidance. Tap any day to see exercises with sets/reps and
  coaching cues.
- **My Nutrition** — daily target (~3000 kcal lean-bulk surplus), 5-meal plan
  with foods + macros per meal, and supplement guidance.
- **My Profile** — body measurements, BMR, TDEE, kcal target, and progression
  notes.

## The plan

**4-day split** (Mon/Tue/Thu/Fri, with active-recovery Wed and full rest Sat/Sun):

| Day | Session            | Focus                                |
|-----|--------------------|--------------------------------------|
| Mon | Upper Power        | Heavy compounds (bench, row, OHP)    |
| Tue | Lower Power        | Squat, RDL, calves, core             |
| Thu | Upper Hypertrophy  | Volume work, arms, lateral delts     |
| Fri | Lower Hypertrophy  | Quads, glutes, hams, calves, abs     |

**Nutrition** — ~3000 kcal/day (Mifflin-St Jeor TDEE 2720 + 300 surplus):
- Protein: 160 g
- Carbs:   430 g
- Fat:      65 g

## Tech stack

- Java, AndroidX, Material Components
- RecyclerView, ConstraintLayout, CardView
- minSdk 24, targetSdk 34

## Build & run

1. Open the project in Android Studio.
2. Build and run on emulator or device — no setup needed; the plan is
   embedded in `data/PlanRepository.java` and `data/UserProfile.java`.

## Customising

To tweak the plan for new measurements, update
`app/src/main/java/com/ayman/fitness/data/UserProfile.java`. Calorie and macro
targets are derived automatically.
