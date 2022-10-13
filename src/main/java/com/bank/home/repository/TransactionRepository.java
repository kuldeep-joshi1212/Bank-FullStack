package com.bank.home.repository;

import com.bank.home.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
	
	@Query(value="select * from user_transaction where userid = :userId order by id desc", nativeQuery = true)
	List<TransactionModel> findAllByUserId(Long userId);
}
