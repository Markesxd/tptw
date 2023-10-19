package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.healthEvent.HealthEvent;
import br.com.ufsj.tptw.model.healthEvent.HealthEventDataOutput;
import br.com.ufsj.tptw.model.healthEvent.HealthEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("health-event")
public class HealthEventController {
  @Autowired
  private HealthEventRepository repository;

  @GetMapping
  public Page<HealthEventDataOutput> fetchAll(@PageableDefault Pageable pageable) {
    return repository.findAll(pageable).map(HealthEventDataOutput::new);
  }

  @PostMapping
  public HealthEventDataOutput create(@RequestBody HealthEvent event) {
    return new HealthEventDataOutput(repository.save(event));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PutMapping
  public void put(@RequestBody HealthEvent healthEvent) {
    Optional<HealthEvent> optionalHealthEvent = repository.findById(healthEvent.getId());
    if(optionalHealthEvent.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
    }
    System.out.println(healthEvent.getRepeatInterval());
    repository.save(healthEvent);
  }
}
