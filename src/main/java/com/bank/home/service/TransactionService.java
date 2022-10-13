package com.bank.home.service;

import com.bank.home.enums.TransactionType;
import com.bank.home.model.AccountModel;
import com.bank.home.model.TransactionModel;

import java.util.List;

public interface TransactionService {
	AccountModel fetchBalance(Long userId);
	
	TransactionModel amountTransaction(TransactionType transactionType, Long userId, Long amount);
	
	List<TransactionModel> fetchTransactionHistory(Long userId);
}
