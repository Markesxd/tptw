package br.com.ufsj.tptw.service;

import br.com.ufsj.tptw.dto.PlanInputDataDTO;
import br.com.ufsj.tptw.dto.PlanOutputData;
import br.com.ufsj.tptw.model.Plan;
import br.com.ufsj.tptw.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
  @Autowired
  private PlanRepository planRepository;

  public void create(PlanInputDataDTO planDTO) {
    planRepository.save(new Plan(planDTO));
  }
  public Page<PlanOutputData> findAll(Pageable pageable) {
    return planRepository.findAll(pageable).map(PlanOutputData::new);
  }
}
