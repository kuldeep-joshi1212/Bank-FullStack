package com.bank.home.repository;

import com.bank.home.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<AccountModel, Long> {
	
	AccountModel findByUserId(Long userId);
	
}
