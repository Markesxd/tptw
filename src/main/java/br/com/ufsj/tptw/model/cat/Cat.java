package br.com.ufsj.tptw.model.cat;

import br.com.ufsj.tptw.model.healthEvent.HealthEvent;
import br.com.ufsj.tptw.model.house.House;
import br.com.ufsj.tptw.model.plan.Plan;
import br.com.ufsj.tptw.model.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table(name="cats")
@Entity
public class Cat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
  private String name;
  private Date birthday;
  private String gender;
  @ManyToOne()
  @JoinColumn(name="user_id", referencedColumnName = "id")
  private User owner;
  @ManyToOne
  @JoinColumn(name = "plan_id")
  private Plan plan;
  @ManyToMany
  private Set<HealthEvent> events;

  public Cat setPlan(Plan plan) {
    this.plan = plan;
    return this;
  }

  public Long getId() {
    return id;
  }

  public Plan getPlan() {
    return plan;
  }

  public String getName() {
    return name;
  }

  public User getOwner() {
    return owner;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getGender() {
    return gender;
  }

}
