package br.com.ufsj.tptw.model.plan;

import br.com.ufsj.tptw.model.cat.CatDataOutput;
import br.com.ufsj.tptw.model.cat.CatHelper;
import br.com.ufsj.tptw.model.plan.meal.Meal;
import br.com.ufsj.tptw.model.plan.meal.MealDataOutput;
import br.com.ufsj.tptw.model.plan.meal.MealHelper;
import br.com.ufsj.tptw.model.user.User;

import java.util.Set;

public record PlanOutputData(String name, Set<MealDataOutput> meals, Set<CatDataOutput> cats) {
  public PlanOutputData(Plan plan) {
    this(plan.getName(), MealHelper.serializeMeals(plan.getMeals()), CatHelper.serializeCats(plan.getCats()));
  }
}
