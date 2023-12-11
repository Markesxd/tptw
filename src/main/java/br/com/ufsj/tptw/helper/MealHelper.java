package br.com.ufsj.tptw.helper;

import br.com.ufsj.tptw.dto.MealDataOutput;
import br.com.ufsj.tptw.dto.PlanInputDataDTO;
import br.com.ufsj.tptw.model.Meal;
import br.com.ufsj.tptw.model.Plan;

import java.util.HashSet;
import java.util.Set;

public class MealHelper {
  public static Set<MealDataOutput> mapFromMeals(Set<Meal> meals) {
    Set<MealDataOutput> mealDto = new HashSet<MealDataOutput>();
    meals.forEach(meal -> {
      mealDto.add(new MealDataOutput(meal));
    });
    return mealDto;
  }

  public static Set<Meal> mapToMeals(Set<MealDataOutput> mealsDTO, Plan plan) {
    Set<Meal> meals = new HashSet<>();
    mealsDTO.forEach(meal -> {
      Meal _meal = new Meal();
      _meal.setPlan(plan);
      _meal.setLabel(meal.label());
      meals.add(_meal);
    });
    return meals;
  }
}
