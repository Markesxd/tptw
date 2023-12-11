package br.com.ufsj.tptw.repository;

import br.com.ufsj.tptw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	User findByEmail(String userEmail);
}
