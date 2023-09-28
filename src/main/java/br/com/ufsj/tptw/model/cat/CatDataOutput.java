package br.com.ufsj.tptw.model.cat;

import br.com.ufsj.tptw.model.user.User;

import java.util.Date;
import java.util.UUID;

public record CatDataOutput(Long id, String name, Date birthday, String gender) {
  public CatDataOutput(Cat cat) {
    this(cat.getId(), cat.getName(), cat.getBirthday(), cat.getGender());
  }
}
