package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.helper.CatHelper;
import br.com.ufsj.tptw.model.Plan;
import br.com.ufsj.tptw.helper.MealHelper;

import java.util.Set;

public record PlanOutputData(Long id, String name, Set<MealDataOutput> meals, Set<CatDataOutput> cats) {
  public PlanOutputData(Plan plan) {
    this(plan.getId(), plan.getName(), MealHelper.mapFromMeals(plan.getMeals()), CatHelper.mapFromCats(plan.getCats()));
  }
}
