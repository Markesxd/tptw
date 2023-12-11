package br.com.ufsj.tptw.helper;

import br.com.ufsj.tptw.dto.PlanOutputData;
import br.com.ufsj.tptw.model.Plan;

import java.util.HashSet;
import java.util.Set;

public class PlanHelper {
  public static Set<PlanOutputData> serializePlans(Set<Plan> plans) {
    Set<PlanOutputData> serializedPlans = new HashSet<>();
    plans.forEach(plan -> {
      serializedPlans.add(new PlanOutputData(plan));
    });
    return serializedPlans;
  }
}
