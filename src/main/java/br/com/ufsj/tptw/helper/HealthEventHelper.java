package br.com.ufsj.tptw.helper;

import br.com.ufsj.tptw.model.HealthEvent;
import br.com.ufsj.tptw.dto.HealthEventDataOutput;

import java.util.HashSet;
import java.util.Set;

public class HealthEventHelper {
  public static Set<HealthEventDataOutput> serializeHealthEvent(Set<HealthEvent> healthEvents) {
    Set<HealthEventDataOutput> serializedHealthEvents = new HashSet<>();
    healthEvents.forEach(healthEvent -> {
      serializedHealthEvents.add(new HealthEventDataOutput(healthEvent));
    });
    return serializedHealthEvents;
  }
}
