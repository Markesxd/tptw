package br.com.ufsj.tptw.repository;

import br.com.ufsj.tptw.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
