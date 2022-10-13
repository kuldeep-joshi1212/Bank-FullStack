package com.bank.home.model;

import com.bank.home.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Data
@Table(name = "user_account")
public class AccountModel extends BaseEntity {
	
	@Column(name = "userid", nullable = false)
	private Long userId;
	
	@Column(name = "account_balance", nullable = false)
	private Long accountBalance;
	
}
