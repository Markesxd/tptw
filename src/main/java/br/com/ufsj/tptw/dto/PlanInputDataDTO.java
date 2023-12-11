package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.helper.CatHelper;
import br.com.ufsj.tptw.helper.MealHelper;
import br.com.ufsj.tptw.model.Plan;

import java.util.Set;

public record PlanInputDataDTO(
  String name,
  Set<MealDataOutput> meals,
  Set<CatDataOutput> cats,
  UserDataOutput user
) {
  public PlanInputDataDTO(Plan plan) {
    this(
      plan.getName(),
      MealHelper.mapFromMeals(plan.getMeals()),
      CatHelper.mapFromCats(plan.getCats()),
      new UserDataOutput(plan.getUser())
    );
  }
}
