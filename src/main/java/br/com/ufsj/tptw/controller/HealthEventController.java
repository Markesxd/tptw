package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.dto.HealthEventDataInputDTO;
import br.com.ufsj.tptw.model.HealthEvent;
import br.com.ufsj.tptw.dto.HealthEventDataOutput;
import br.com.ufsj.tptw.repository.HealthEventRepository;
import br.com.ufsj.tptw.service.HealtEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("health-event")
public class HealthEventController {
  @Autowired
  private HealtEventService healtEventService;

  @GetMapping
  public Page<HealthEventDataOutput> fetchAll(@PageableDefault Pageable pageable) {
    return healtEventService.findAll(pageable);
  }

  @PostMapping
  public void create(@RequestBody HealthEventDataInputDTO event) {
    healtEventService.create(event);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    healtEventService.delete(id);
  }

  @PutMapping
  public void put(@RequestBody HealthEventDataOutput healthEventDTO) {
    healtEventService.update(healthEventDTO);
  }
}
