package com.bank.home.model;

import com.bank.home.entity.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Component
@Data
@Entity
@Table(name = "users")
public class UserModel extends BaseEntity {
	
	@Column(name = "email", nullable = false, unique = true)
	private String userEmail;
	
	@Column(name = "username", unique = true, nullable = false)
	private String userName;
	
	@Column(name = "user_password", nullable = false)
	private String userPassword;
	
}
