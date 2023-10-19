package br.com.ufsj.tptw.model.healthEvent;

import java.util.Date;

public record HealthEventDataOutput(Long id, String name, Date date, RepeatInterval repeatInterval) {
  public HealthEventDataOutput(HealthEvent healthEvent) {
    this(healthEvent.getId(), healthEvent.getName(), healthEvent.getDate(), healthEvent.getRepeatInterval());
  }
}
