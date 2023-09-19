package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserRepository repository;
	@GetMapping
	public Page<UserDataOutput> fetchAll(@PageableDefault(sort = {"id"})Pageable pagination) {
		return repository.findAll(pagination).map(UserDataOutput::new);
	}

	@PostMapping
	public void create(@RequestBody User user) {
		repository.save(user);
	}

  @PostMapping("/login")
  public UUID login(@RequestBody User user) {
    try {
      User match = repository.findOne(Example.of(user)).orElseThrow();
      return match.getId();
    } catch (NoSuchElementException exception) {
      throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to Login", exception);
    }
  }
}
