package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.cat.CatRepository;
import br.com.ufsj.tptw.model.plan.Plan;
import br.com.ufsj.tptw.model.plan.PlanOutputData;
import br.com.ufsj.tptw.model.plan.PlanRepository;
import br.com.ufsj.tptw.model.plan.meal.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("plan")
public class PlanController {
  @Autowired
  private PlanRepository planRepository;
  @Autowired
  private MealRepository mealRepository;
  @Autowired
  private CatRepository catRepository;

  @PostMapping
  public void postPlan(@RequestBody Plan plan) {
    planRepository.save(plan);
      plan.getMeals()
        .forEach(meal -> {
          meal.setPlan(plan);
          mealRepository.save(meal);
        });
    plan.getCats()
      .forEach(cat -> {
      Optional<Cat> repoCat = catRepository.findById(cat.getId());
      if(repoCat.isPresent()) {
        catRepository.save(
          repoCat.get().setPlan(plan)
        );
      }
    });
  }

  @GetMapping
  public Page<PlanOutputData> getPlans(@PageableDefault Pageable pagination) {
    return planRepository.findAll(pagination).map(PlanOutputData::new);
  }
}
