package br.com.ufsj.tptw.model.user;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "user")
@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String password;
	private String email;


	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
