package br.com.ufsj.tptw.service;

import br.com.ufsj.tptw.dto.HealthEventDataInputDTO;
import br.com.ufsj.tptw.dto.HealthEventDataOutput;
import br.com.ufsj.tptw.model.HealthEvent;
import br.com.ufsj.tptw.repository.HealthEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HealtEventService {
  @Autowired
  private HealthEventRepository healthEventRepository;

  public void create(HealthEventDataInputDTO healthEventDTO) {
    healthEventRepository.save(new HealthEvent(healthEventDTO));
  }

  public Page<HealthEventDataOutput> findAll(Pageable pageable) {
    return healthEventRepository.findAll(pageable).map(HealthEventDataOutput::new);
  }

  public void delete(Long id) {
    healthEventRepository.deleteById(id);
  }

  public void update(HealthEventDataOutput healthEventDTO) {
    HealthEvent healthEvent = new HealthEvent(healthEventDTO);
    if(healthEventRepository.existsById(healthEvent.getId())) {
      healthEventRepository.save(healthEvent);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
    }
  }
}
