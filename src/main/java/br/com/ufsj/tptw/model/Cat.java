package br.com.ufsj.tptw.model;

import br.com.ufsj.tptw.dto.CatDTO;
import br.com.ufsj.tptw.dto.CatDataOutput;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

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

  @Override
  public String toString() {
    return "Cat{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", birthday=" + birthday +
      ", gender='" + gender + '\'' +
      ", owner=" + owner +
      ", plan=" + plan +
      ", events=" + events +
      '}';
  }

  public Cat() { }

  public Cat(CatDTO cat) {
    this.name = cat.name();
    this.birthday = cat.birthday();
    this.gender = cat.gender();
    User owner = new User();
    owner.setId(cat.owner().id());
    this.owner = owner;
  }

  public Cat(CatDataOutput cat) {
    this.id = cat.id();
    this.name = cat.name();
    this.birthday = cat.birthday();
    this.gender = cat.gender();
  }

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
