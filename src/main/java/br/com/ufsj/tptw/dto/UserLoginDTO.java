package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.model.User;

public record UserLoginDTO(String email, String password) {
  UserLoginDTO(User user) {
    this(user.getEmail(), user.getPassword());
  }
}
