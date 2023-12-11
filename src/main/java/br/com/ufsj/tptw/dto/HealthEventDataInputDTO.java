package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.enums.RepeatInterval;
import br.com.ufsj.tptw.helper.CatHelper;
import br.com.ufsj.tptw.model.HealthEvent;

import java.util.Date;
import java.util.Set;

public record HealthEventDataInputDTO(String name, Date date, RepeatInterval repeatInterval, UserDataOutput user, Set<CatDataOutput> cats) {
  HealthEventDataInputDTO(HealthEvent healthEvent) {
    this(healthEvent.getName(), healthEvent.getDate(),healthEvent.getRepeatInterval(), new UserDataOutput(healthEvent.getUser()), CatHelper.mapFromCats(healthEvent.getCats()));
  }
}
