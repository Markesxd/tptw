package br.com.ufsj.tptw.model;

import br.com.ufsj.tptw.dto.MealDataOutput;
import br.com.ufsj.tptw.model.Plan;
import jakarta.persistence.*;

import java.util.Set;

@Table(name="meals")
@Entity
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String label;
  @ManyToOne()
  @JoinColumn(name="plan_id")
  private Plan plan;

  public Meal() {}
  public Meal(MealDataOutput mealDTO) {
    label = mealDTO.label();
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public long getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public Plan getPlan() {
    return plan;
  }

  @Override
  public String toString() {
    return "Meal{" +
      "id=" + id +
      ", label='" + label + '\'' +
      ", plan=" + plan +
      '}';
  }
}
