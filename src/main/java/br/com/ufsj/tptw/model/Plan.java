package br.com.ufsj.tptw.model;

import br.com.ufsj.tptw.dto.PlanInputDataDTO;
import br.com.ufsj.tptw.helper.CatHelper;
import br.com.ufsj.tptw.helper.MealHelper;
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
  @OneToMany(mappedBy = "plan", cascade = CascadeType.PERSIST)
  private Set<Meal> meals;
  @OneToMany(mappedBy = "plan")
  private Set<Cat> cats;

  public Plan() {}
  public Plan(PlanInputDataDTO planDTO) {
    name = planDTO.name();
    user = new User(planDTO.user());
    meals = MealHelper.mapToMeals(planDTO.meals(), this);
    cats = CatHelper.mapToCats(planDTO.cats());
  }

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

  @Override
  public String toString() {
    return "Plan{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", user=" + user +
      ", meals=" + meals +
      ", cats=" + cats +
      '}';
  }
}
