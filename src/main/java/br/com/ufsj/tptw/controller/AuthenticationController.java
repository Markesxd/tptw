package br.com.ufsj.tptw.controller;

import br.com.ufsj.tptw.dto.UserLoginDTO;
import br.com.ufsj.tptw.model.User;
import br.com.ufsj.tptw.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
  @Autowired
  private TokenService tokenService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity login(@RequestBody UserLoginDTO loginDTO) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
    var authentication = authenticationManager.authenticate(authenticationToken);
    var token = tokenService.generateToken((String) authentication.getPrincipal());
    return ResponseEntity.ok(new TokenDTO(token));
  }

  private record TokenDTO(String token) {}
}
