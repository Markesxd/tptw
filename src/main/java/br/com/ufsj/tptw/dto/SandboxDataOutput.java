package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.model.Sandbox;

import java.util.Date;

public record SandboxDataOutput(Long id, String name, Date cleanDate) {
  public SandboxDataOutput(Sandbox sandbox) {
    this(sandbox.getId(), sandbox.getName(), sandbox.getCleanDate());
  }
}
