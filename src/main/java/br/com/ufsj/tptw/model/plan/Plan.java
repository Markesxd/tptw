package br.com.ufsj.tptw.model.plan;

import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.plan.meal.Meal;
import br.com.ufsj.tptw.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Table(name = "plans")
@Entity()
public class Plan {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  private String name;
  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;
  @OneToMany(mappedBy = "plan")
  private Set<Meal> meals;
  @OneToMany(mappedBy = "plan")
  private Set<Cat> cats;

  public void setCats(Set<Cat> cats) {
    this.cats = cats;
  }

  public Set<Cat> getCats() {
    return cats;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Meal> getMeals() {
    return meals;
  }

  public void setMeals(Set<Meal> meals) {
    this.meals = meals;
  }
}
