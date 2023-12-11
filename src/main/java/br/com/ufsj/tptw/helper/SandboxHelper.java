package br.com.ufsj.tptw.helper;

import br.com.ufsj.tptw.dto.SandboxDataOutput;
import br.com.ufsj.tptw.model.Sandbox;

public class SandboxHelper {
  public static void copyInto(SandboxDataOutput sandboxDTO, Sandbox sandbox) {
      if(sandboxDTO.name() != null) {
        sandbox.setName(sandboxDTO.name());
      }
      if(sandboxDTO.cleanDate() != null) {
        sandbox.setCleanDate(sandboxDTO.cleanDate());
      }
  }
}
