package br.com.ufsj.tptw.model.cat;

import java.util.Date;

public record CatDataOutput(String name, Date birthday, String gender) {
  public CatDataOutput(Cat cat) {
    this(cat.getName(), cat.getBirthday(), cat.getGender());
  }
}
