package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.dto.PlanInputDataDTO;
import br.com.ufsj.tptw.model.Cat;
import br.com.ufsj.tptw.repository.CatRepository;
import br.com.ufsj.tptw.model.Plan;
import br.com.ufsj.tptw.dto.PlanOutputData;
import br.com.ufsj.tptw.repository.PlanRepository;
import br.com.ufsj.tptw.repository.MealRepository;
import br.com.ufsj.tptw.service.PlanService;
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
  private PlanService planService;

  @PostMapping
  public void postPlan(@RequestBody PlanInputDataDTO plan) {
    planService.create(plan);
  }

  @GetMapping
  public Page<PlanOutputData> getPlans(@PageableDefault Pageable pagination) {
    return planService.findAll(pagination);
  }
}
