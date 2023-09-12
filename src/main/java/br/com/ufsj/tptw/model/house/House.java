package br.com.ufsj.tptw.model.house;

import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;

@Table(name="houses")
@Entity
public class House {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne
	private User owner;
	@OneToMany
	private ArrayList<Cat> cats;
}
