package br.com.ufsj.tptw.service;

import br.com.ufsj.tptw.dto.SandboxDataOutput;
import br.com.ufsj.tptw.helper.SandboxHelper;
import br.com.ufsj.tptw.model.Sandbox;
import br.com.ufsj.tptw.repository.SandboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SandboxService {
  @Autowired
  private SandboxRepository sandboxRepository;

  public void create(SandboxDataOutput sandboxDTO) {
    this.sandboxRepository.save(new Sandbox(sandboxDTO));
  }

  public SandboxDataOutput getOne(Long id) {
    try {
      return sandboxRepository.findById(id).map(SandboxDataOutput::new).orElseThrow();
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sandbox not foud", exception);
    }
  }

  public Page<SandboxDataOutput> getAll(Pageable pageable) {
    return sandboxRepository.findAll(pageable).map(SandboxDataOutput::new);
  }

  public void update(SandboxDataOutput sandboxDTO) {
    try {
      Sandbox sandbox = sandboxRepository.findById(sandboxDTO.id()).orElseThrow();
      SandboxHelper.copyInto(sandboxDTO, sandbox);
      sandboxRepository.save(sandbox);
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id inválido", exception);
    }
  }

  public void deleteById(Long id) {
    sandboxRepository.deleteById(id);

  }

  public void clean(Long id) {
    try {
      Sandbox sandbox = sandboxRepository.findById(id).orElseThrow();
      sandbox.setCleanDate(new Date());
      sandboxRepository.save(sandbox);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sandbox found", e);
    }
  }
}
