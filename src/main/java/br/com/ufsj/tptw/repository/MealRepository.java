package br.com.ufsj.tptw.repository;

import br.com.ufsj.tptw.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
