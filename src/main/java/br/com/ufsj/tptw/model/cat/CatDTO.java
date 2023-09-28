package br.com.ufsj.tptw.model.cat;

import br.com.ufsj.tptw.model.user.UserDataOutput;

import java.util.Date;

public record CatDTO(String name, Date birthday, String gender, UserDataOutput owner) {
  public CatDTO(Cat cat) {
    this(cat.getName(), cat.getBirthday(), cat.getGender(), new UserDataOutput(cat.getOwner()));
  }
}
