package br.com.ufsj.tptw.model.plan.meal;

public record MealDataOutput(String label) {
  MealDataOutput(Meal meal) {
    this(meal.getLabel());
  }
}
