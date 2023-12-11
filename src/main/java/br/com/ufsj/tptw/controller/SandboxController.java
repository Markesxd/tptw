package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.Sandbox;
import br.com.ufsj.tptw.dto.SandboxDataOutput;
import br.com.ufsj.tptw.repository.SandboxRepository;
import br.com.ufsj.tptw.service.SandboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/sandbox")
public class SandboxController {
  @Autowired
  private SandboxRepository sandboxRepository;
  @Autowired
  private SandboxService sandboxService;

  @PostMapping
  public void create(@RequestBody SandboxDataOutput sandbox) {
    sandboxService.create(sandbox);
  }


  @GetMapping("/{id}")
  public SandboxDataOutput getOne(@PathVariable Long id) {
    return sandboxService.getOne(id);
  }

  @GetMapping
  public Page<SandboxDataOutput> getAll(@PageableDefault Pageable pageable) {
    return sandboxService.getAll(pageable);
  }

  @PutMapping()
  public void put(@RequestBody SandboxDataOutput sandboxDTO) {
      sandboxService.update(sandboxDTO);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    sandboxService.deleteById(id);
  }

  @PatchMapping("/{id}/clean")
  public void clean(@PathVariable Long id) {
    sandboxService.clean(id);
  }
}
