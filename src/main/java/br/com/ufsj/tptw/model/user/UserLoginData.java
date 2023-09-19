package br.com.ufsj.tptw.model.user;

public record UserLoginData(String email, String password) {
  UserLoginData(User user) {
    this(user.getEmail(), user.getPassword());
  }
}
