package br.com.ufsj.tptw.model.plan.meal;

import java.util.HashSet;
import java.util.Set;

public class MealHelper {
  public static Set<MealDataOutput> serializeMeals(Set<Meal> meals) {
    Set<MealDataOutput> serializedMeals = new HashSet<MealDataOutput>();
    meals.forEach(meal -> {
      serializedMeals.add(new MealDataOutput(meal));
    });
    return serializedMeals;
  }
}
