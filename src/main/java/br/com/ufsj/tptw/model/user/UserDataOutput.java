package br.com.ufsj.tptw.model.user;

public record UserDataOutput (
	String name,
	String email
) {
	public UserDataOutput(User user) {
		this(user.getName(), user.getEmail());
	}
}
