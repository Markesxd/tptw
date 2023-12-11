package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.enums.RepeatInterval;
import br.com.ufsj.tptw.helper.CatHelper;
import br.com.ufsj.tptw.model.HealthEvent;

import java.util.Date;
import java.util.Set;

public record HealthEventDataOutput(
    Long id,
    String name,
    Date date,
    RepeatInterval repeatInterval,
    Set<CatDataOutput> cats,
    UserDataOutput user
) {
  public HealthEventDataOutput(HealthEvent healthEvent) {
    this(
      healthEvent.getId(),
      healthEvent.getName(),
      healthEvent.getDate(),
      healthEvent.getRepeatInterval(),
      CatHelper.mapFromCats(healthEvent.getCats()),
      new UserDataOutput(healthEvent.getUser()));
  }
}
