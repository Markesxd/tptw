package br.com.ufsj.tptw.model.plan;

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
