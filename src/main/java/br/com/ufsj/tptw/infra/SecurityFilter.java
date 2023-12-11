package br.com.ufsj.tptw.infra;

import br.com.ufsj.tptw.model.User;
import br.com.ufsj.tptw.repository.UserRepository;
import br.com.ufsj.tptw.service.TokenService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class SecurityFilter extends OncePerRequestFilter implements SmartInitializingSingleton {
  private AuthenticationManagerBuilder authMgrBuilder;
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = getToken(request.getHeader("Authorization"));
    if(token != null) {
      String userEmail = tokenService.authenticateToken(token);
      User user = userRepository.findByEmail(userEmail);
      var authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request, response);
  }

  private String getToken(String authorization) {
    if(authorization != null) {
      return authorization.replace("Bearer " , "");
    }
    return null;
  }

  public SecurityFilter(AuthenticationManagerBuilder authMgrBuilder) {
    this.authMgrBuilder = authMgrBuilder;
  }

  @Override
  public void afterSingletonsInstantiated() {
    this.authenticationManager = authMgrBuilder.getObject();
  }
}
