package br.com.ufsj.tptw.repository;

import br.com.ufsj.tptw.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
