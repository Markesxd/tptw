package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.cat.CatDTO;
import br.com.ufsj.tptw.model.cat.CatDataOutput;
import br.com.ufsj.tptw.model.cat.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cat")
public class CatController {
  @Autowired
  private CatRepository repository;

  @GetMapping
  public Page<CatDataOutput> fetchAll(@PageableDefault(sort = "id")Pageable pageable) {
    return repository.findAll(pageable).map(CatDataOutput::new);
  }

  @PostMapping
  public void create(@RequestBody Cat cat) {
    repository.save(cat);
  }

  @GetMapping("/{id}")
  public CatDTO get(@PathVariable Long id) {
    try {
      return repository.findById(id).map(CatDTO::new).orElseThrow();
    } catch (NoSuchElementException exp) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cat found", exp);
    }
  }
}
