package br.com.ufsj.tptw.service;

import br.com.ufsj.tptw.model.User;
import br.com.ufsj.tptw.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
  @Value("api.security.secret")
  private String secret;
  @Autowired
  private UserRepository userRepository;

  public String generateToken(String userEmail) {
    User user = userRepository.findByEmail(userEmail);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
      .withIssuer("JDC_API")
      .withSubject(userEmail)
      .withClaim("id", user.getId().toString())
      .withExpiresAt(expiration())
      .sign(algorithm);
  }

  public String authenticateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    try {
      return JWT.require(algorithm)
        .withIssuer("JDC_API")
        .build()
        .verify(token)
        .getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Invalid Token!");
    }
  }

  private Instant expiration() {
    return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
  }
}
