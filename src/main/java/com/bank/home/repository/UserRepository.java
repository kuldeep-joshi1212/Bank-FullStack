package com.bank.home.repository;

import com.bank.home.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	Optional<UserModel> findByUserEmailAndUserPassword(String email, String password);
	
	Optional<UserModel> findByUserEmail(String email);
	
	Optional<UserModel> findByUserName(String userName);
	
}
