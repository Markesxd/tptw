package br.com.ufsj.tptw.model.user;
import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.cat.CatDataOutput;
import br.com.ufsj.tptw.model.plan.Plan;
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
