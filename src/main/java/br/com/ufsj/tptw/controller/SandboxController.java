package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.sandbox.Sandbox;
import br.com.ufsj.tptw.model.sandbox.SandboxDataOutput;
import br.com.ufsj.tptw.model.sandbox.SandboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sandbox")
public class SandboxController {
  @Autowired
  private SandboxRepository sandboxRepository;


  @PostMapping
  public void create(@RequestBody Sandbox sandbox) {
    this.sandboxRepository.save(sandbox);
  }


  @GetMapping("/{id}")
  public SandboxDataOutput getOne(@PathVariable Long id) {
    try {
      return sandboxRepository.findById(id).map(SandboxDataOutput::new).orElseThrow();
    } catch (NoSuchElementException exception) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sandbox not foud", exception);
    }
  }

  @GetMapping
  public Set<SandboxDataOutput> get() {
      return sandboxRepository.findAll().stream().map(SandboxDataOutput::new).collect(Collectors.toSet());
  }

  @PutMapping()
  public void put(@RequestBody Sandbox sandbox) {
    try {
      sandboxRepository.findById(sandbox.getId()).orElseThrow();
      sandboxRepository.save(sandbox);
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id inválido", exception);
    }
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    sandboxRepository.deleteById(id);
  }

  @PatchMapping("/{id}/clean")
  public void clean(@PathVariable Long id) {
    try {
      Sandbox sandbox = sandboxRepository.findById(id).orElseThrow();
      sandbox.setCleanDate(new Date());
      sandboxRepository.save(sandbox);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sandbox found", e);
    }
  }
}
