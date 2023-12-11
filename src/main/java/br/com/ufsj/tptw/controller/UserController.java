package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.dto.*;
import br.com.ufsj.tptw.helper.HealthEventHelper;
import br.com.ufsj.tptw.model.User;
import br.com.ufsj.tptw.repository.HealthEventRepository;
import br.com.ufsj.tptw.helper.PlanHelper;
import br.com.ufsj.tptw.repository.UserRepository;
import br.com.ufsj.tptw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserRepository repository;
  @Autowired
  private HealthEventRepository healthEventRepository;
  @Autowired
  private UserService userService;


	@GetMapping
	public Page<UserDataOutput> fetchAll(@PageableDefault(sort = {"id"})Pageable pagination) {
    return userService.getAll(pagination);
  }

	@PostMapping
	public void create(@RequestBody UserInputDTO user) {
		userService.create(user);
	}

  @PostMapping("/login")
  public UUID login(@RequestBody UserLoginDTO user) {
    return userService.login(user);
  }

  @GetMapping("/{id}")
  public UserDataOutput fetch(@PathVariable UUID id) {
    return userService.getOne(id);
  }

  @GetMapping("/{id}/cats")
  public UserCatsDataOutput fetchCats(@PathVariable UUID id) {
    return userService.getCats(id);
  }

  @GetMapping("/{id}/plans")
  public Set<PlanOutputData> fetchPlans(@PathVariable UUID id) {
    return userService.getPlans(id);
  }

  @GetMapping("/{id}/health-events")
  public Set<HealthEventDataOutput> fetchHealthEvents(@PathVariable UUID id) {
    return userService.getHealthEvents(id);
  }

  @GetMapping("{id}/sandboxes")
  public Set<SandboxDataOutput> fetchSandboxes(@PathVariable UUID id) {
    return userService.getSandboxes(id);
  }
}
