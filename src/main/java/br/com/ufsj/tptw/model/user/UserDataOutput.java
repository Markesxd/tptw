package br.com.ufsj.tptw.model.user;

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
