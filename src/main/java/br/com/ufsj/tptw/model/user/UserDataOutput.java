package br.com.ufsj.tptw.model.user;

public record UserDataOutput (
	String userName,
	String email
) {
	public UserDataOutput(User user) {
		this(user.getUserName(), user.getEmail());
	}
}