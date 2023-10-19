package br.com.ufsj.tptw.model.healthEvent;

import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="health_events")
public class HealthEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private Date date;
  private RepeatInterval repeatInterval;
  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToMany
  @JoinTable(
    name="health_events_cats",
    joinColumns = @JoinColumn(name="health_event_id"),
    inverseJoinColumns =  @JoinColumn(name="cat_id")
  )
  private Set<Cat> cats;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public RepeatInterval getRepeatInterval() {
    return repeatInterval;
  }

  public void setRepeatInterval(RepeatInterval repeatInterval) {
    this.repeatInterval = repeatInterval;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Cat> getCats() {
    return cats;
  }

  public void setCats(Set<Cat> cats) {
    this.cats = cats;
  }
}
