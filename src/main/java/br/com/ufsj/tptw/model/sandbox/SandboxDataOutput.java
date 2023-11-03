package br.com.ufsj.tptw.model.sandbox;

import br.com.ufsj.tptw.model.user.User;

import java.util.Date;

public record SandboxDataOutput(Long id, String name, Date cleanDate) {
  public SandboxDataOutput(Sandbox sandbox) {
    this(sandbox.getId(), sandbox.getName(), sandbox.getCleanDate());
  }
}
