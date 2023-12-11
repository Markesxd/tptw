package br.com.ufsj.tptw.model;

import br.com.ufsj.tptw.dto.HealthEventDataInputDTO;
import br.com.ufsj.tptw.dto.HealthEventDataOutput;
import br.com.ufsj.tptw.enums.RepeatInterval;
import br.com.ufsj.tptw.helper.CatHelper;
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

  public HealthEvent() {}
  public HealthEvent(HealthEventDataInputDTO healthEventDTO) {
    this.date = healthEventDTO.date();
    this.name = healthEventDTO.name();
    this.repeatInterval = healthEventDTO.repeatInterval();
    User user = new User();
    user.setId(healthEventDTO.user().id());
    this.user = user;
    this.cats = CatHelper.mapToCats(healthEventDTO.cats());
  }

  public HealthEvent(HealthEventDataOutput healthEventDTO) {
    this.id = healthEventDTO.id();
    this.date = healthEventDTO.date();
    this.name = healthEventDTO.name();
    this.repeatInterval = healthEventDTO.repeatInterval();
    User user = new User();
    user.setId(healthEventDTO.user().id());
    this.user = user;
    this.cats = CatHelper.mapToCats(healthEventDTO.cats());
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

  @Override
  public String toString() {
    return "HealthEvent{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", date=" + date +
      ", repeatInterval=" + repeatInterval +
      ", user=" + user +
      ", cats=" + cats +
      '}';
  }
}
