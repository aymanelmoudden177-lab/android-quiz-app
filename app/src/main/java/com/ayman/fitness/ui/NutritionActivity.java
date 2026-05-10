package com.ayman.fitness.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.fitness.R;
import com.ayman.fitness.data.Meal;
import com.ayman.fitness.data.PlanRepository;
import com.ayman.fitness.data.UserProfile;

import java.util.List;

public class NutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        UserProfile p = UserProfile.get();
        TextView kcal = findViewById(R.id.tv_kcal);
        kcal.setText(p.targetKcal() + " kcal / day");

        TextView macros = findViewById(R.id.tv_macros);
        macros.setText(String.format(
            "Protein %dg  ·  Carbs %dg  ·  Fat %dg",
            p.targetProteinGrams(), p.targetCarbGrams(), p.targetFatGrams()
        ));

        TextView basis = findViewById(R.id.tv_basis);
        basis.setText(String.format(
            "BMR %d kcal · TDEE %d kcal · Surplus +300 (lean bulk)",
            p.bmr(), p.tdee()
        ));

        List<Meal> meals = PlanRepository.dailyMeals();
        RecyclerView rv = findViewById(R.id.rv_meals);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MealAdapter(meals));

        int totalKcal = 0, totalP = 0, totalC = 0, totalF = 0;
        for (Meal m : meals) {
            totalKcal += m.kcal; totalP += m.proteinG;
            totalC += m.carbsG; totalF += m.fatG;
        }
        TextView totals = findViewById(R.id.tv_totals);
        totals.setText(String.format(
            "Plan totals: %d kcal · P %dg · C %dg · F %dg",
            totalKcal, totalP, totalC, totalF
        ));

        LinearLayout suppList = findViewById(R.id.layout_supps);
        for (String s : PlanRepository.supplements()) {
            TextView t = new TextView(this);
            t.setText("• " + s);
            t.setTextSize(14f);
            t.setPadding(0, 6, 0, 6);
            suppList.addView(t);
        }
    }

    static class MealAdapter extends RecyclerView.Adapter<MealHolder> {
        private final List<Meal> items;
        MealAdapter(List<Meal> items) { this.items = items; }

        @NonNull @Override
        public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_meal, parent, false);
            return new MealHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MealHolder h, int position) {
            Meal m = items.get(position);
            h.name.setText(m.name);
            h.foods.setText(m.foods);
            h.macros.setText(String.format(
                "%d kcal · P %dg · C %dg · F %dg",
                m.kcal, m.proteinG, m.carbsG, m.fatG
            ));
        }

        @Override public int getItemCount() { return items.size(); }
    }

    static class MealHolder extends RecyclerView.ViewHolder {
        TextView name, foods, macros;
        MealHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tv_meal_name);
            foods = v.findViewById(R.id.tv_meal_foods);
            macros = v.findViewById(R.id.tv_meal_macros);
        }
    }
}
