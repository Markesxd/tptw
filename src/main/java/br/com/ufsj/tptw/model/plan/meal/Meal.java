package br.com.ufsj.tptw.model.plan.meal;

import br.com.ufsj.tptw.model.plan.Plan;
import jakarta.persistence.*;

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
}
