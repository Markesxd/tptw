package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.dto.CatDTO;
import br.com.ufsj.tptw.dto.CatDataOutput;
import br.com.ufsj.tptw.repository.CatRepository;
import br.com.ufsj.tptw.service.CatService;
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
  private CatService catService;

  @GetMapping
  public Page<CatDataOutput> fetchAll(@PageableDefault(sort = "id")Pageable pageable) {
    return catService.findAll(pageable);
  }

  @PostMapping
  public void create(@RequestBody CatDTO cat) {
    catService.createCat(cat);
  }

  @GetMapping("/{id}")
  public CatDTO get(@PathVariable Long id) {
    try {
      return catService.findById(id);
    } catch (NoSuchElementException exp) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cat found", exp);
    }
  }
}
