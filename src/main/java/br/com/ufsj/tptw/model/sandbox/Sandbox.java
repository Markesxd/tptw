package br.com.ufsj.tptw.model.sandbox;

import br.com.ufsj.tptw.model.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Table(name="sandboxes")
@Entity
public class Sandbox {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Date cleanDate;

  @ManyToOne()
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCleanDate() {
    return cleanDate;
  }

  public void setCleanDate(Date cleanDate) {
    this.cleanDate = cleanDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
