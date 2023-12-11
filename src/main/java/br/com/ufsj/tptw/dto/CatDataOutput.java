package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.model.Cat;

import java.util.Date;

public record CatDataOutput(Long id, String name, Date birthday, String gender) {
  public CatDataOutput(Cat cat) {
    this(cat.getId(), cat.getName(), cat.getBirthday(), cat.getGender());
  }
}
