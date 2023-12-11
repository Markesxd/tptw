package br.com.ufsj.tptw.repository;

import br.com.ufsj.tptw.model.HealthEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthEventRepository extends JpaRepository<HealthEvent, Long> {
}
