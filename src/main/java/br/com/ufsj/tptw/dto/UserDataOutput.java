package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.model.User;

import java.util.UUID;

public record UserDataOutput (
  UUID id,
	String name,
	String email
) {
	public UserDataOutput(User user) {
		this(user.getId(), user.getName(), user.getEmail());
	}
}
