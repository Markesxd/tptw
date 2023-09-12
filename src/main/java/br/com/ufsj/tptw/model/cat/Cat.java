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
	private String name;
	private Date birthday;
	private CatGender gender;
	@ManyToOne
	private House house;
}
