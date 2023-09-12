package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.model.user.User;
import br.com.ufsj.tptw.model.user.UserDataOutput;
import br.com.ufsj.tptw.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
}
