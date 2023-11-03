package br.com.ufsj.tptw.model.user;
import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.cat.CatDataOutput;
import br.com.ufsj.tptw.model.healthEvent.HealthEvent;
import br.com.ufsj.tptw.model.plan.Plan;
import br.com.ufsj.tptw.model.sandbox.Sandbox;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Entity
public class User {
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

  public String getPassword() {
    return password;
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
