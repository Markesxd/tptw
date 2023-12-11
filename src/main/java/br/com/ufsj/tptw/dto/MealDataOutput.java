package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.model.Meal;

public record MealDataOutput(String label) {
  public MealDataOutput(Meal meal) {
    this(meal.getLabel());
  }
}
