package br.com.ufsj.tptw.model.cat;

import br.com.ufsj.tptw.model.house.House;
import jakarta.persistence.*;

import java.util.Date;

@Table(name="cats")
@Entity
public class Cat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

  public String getName() {
    return name;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getGender() {
    return gender;
  }

  private String name;
	private Date birthday;
	private String gender;
//	@ManyToOne
//	private House house;
}
