package br.com.ufsj.tptw.service;

import br.com.ufsj.tptw.dto.*;
import br.com.ufsj.tptw.helper.HealthEventHelper;
import br.com.ufsj.tptw.helper.PlanHelper;
import br.com.ufsj.tptw.model.User;
import br.com.ufsj.tptw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public Page<UserDataOutput> getAll(Pageable pageable) {
    return userRepository.findAll(pageable).map(UserDataOutput::new);
  }

  public void create(UserInputDTO userDTO) {
    User user = new User(userDTO);
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(new User(userDTO));
  }

  public UUID login(UserLoginDTO userDTO) {
    try {
      User match = userRepository.findOne(Example.of(new User(userDTO))).orElseThrow();
      return match.getId();
    } catch (NoSuchElementException exception) {
      throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to Login", exception);
    }
  }

  public UserDataOutput getOne(UUID id) {
    try {
      return userRepository.findById(id).map(UserDataOutput::new).orElseThrow();
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found", exception);
    }
  }

  public UserCatsDataOutput getCats(UUID id) {
    try {
      return userRepository.findById(id).map(UserCatsDataOutput::new).orElseThrow();
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found", exception);
    }
  }

  public Set<PlanOutputData> getPlans(UUID id) {
    try {
      User user = userRepository.findById(id).orElseThrow();
      return PlanHelper.serializePlans(user.getPlans());
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found", exception);
    }
  }
  public Set<HealthEventDataOutput> getHealthEvents(UUID id) {
    try {
      User user = userRepository.findById(id).orElseThrow();
      return HealthEventHelper.serializeHealthEvent(user.getHealthEvents());
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exception);
    }
  }

  public Set<SandboxDataOutput> getSandboxes(UUID id) {
    try {
      return userRepository.findById(id).map(_user -> {
        Set<SandboxDataOutput> sandboxes = new HashSet<>();
        _user.getSandboxes().forEach(_sandbox -> {
          sandboxes.add(new SandboxDataOutput(_sandbox));
        });
        return sandboxes;
      }).orElseThrow();
    } catch (NoSuchElementException e) {
      return new HashSet<>();
    }
  }
}
