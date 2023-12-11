package br.com.ufsj.tptw.model;
import br.com.ufsj.tptw.dto.UserInputDTO;
import br.com.ufsj.tptw.dto.UserDataOutput;
import br.com.ufsj.tptw.dto.UserLoginDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "users")
@Entity
public class User implements UserDetails {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String password;
	private String email;
  @JsonIgnore
  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
  private Set<Cat> cats = new HashSet<Cat>();
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<Plan> plans;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<HealthEvent> healthEvents;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private Set<Sandbox> sandboxes;

  public User() {}
  public User(UserDataOutput userDTO) {
    id = userDTO.id();
  }

  public User(UserInputDTO userDTO) {
    name = userDTO.name();
    email = userDTO.email();
    password = userDTO.password();
  }

  public User(UserLoginDTO userDTO) {
    email = userDTO.email();
    password = userDTO.password();
  }

  public Set<Sandbox> getSandboxes() {
    return sandboxes;
  }

  public void setSandboxes(Set<Sandbox> sandboxes) {
    this.sandboxes = sandboxes;
  }

  public Set<HealthEvent> getHealthEvents() {
    return healthEvents;
  }

  public void setHealthEvents(Set<HealthEvent> healthEvents) {
    this.healthEvents = healthEvents;
  }

  public Set<Plan> getPlans() {
    return plans;
  }

  public Set<Cat> getCats() {
    return cats;
  }
public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("USER"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
