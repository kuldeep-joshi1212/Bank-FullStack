package com.bank.home.service.implementation;

import com.bank.home.enums.TransactionType;
import com.bank.home.model.AccountModel;
import com.bank.home.model.TransactionModel;
import com.bank.home.repository.TransactionRepository;
import com.bank.home.repository.UserAccountRepository;
import com.bank.home.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public AccountModel fetchBalance(Long userId) {
		return userAccountRepository.findByUserId(userId);
	}
	
	@Override
	public TransactionModel amountTransaction(TransactionType transactionType, Long userId, Long amount) {
		TransactionModel save;
		AccountModel accountDetails = fetchBalance(userId);
		switch(transactionType) {
			case CREDIT:
				//Insert Into Transaction Table
				TransactionModel transactionModelCredit = new TransactionModel();
				transactionModelCredit.setUserId(userId);
				transactionModelCredit.setAccountId(accountDetails.getId());
				transactionModelCredit.setTransactionType(TransactionType.CREDIT.name());
				transactionModelCredit.setTransactionAmount(amount);
				save = transactionRepository.save(transactionModelCredit);
				
				//Update Balance in UserAccount
				AccountModel accountModelCredit = new AccountModel();
				accountModelCredit.setId(accountDetails.getId());
				accountModelCredit.setUserId(userId);
				accountModelCredit.setAccountBalance(accountDetails.getAccountBalance() + amount);
				userAccountRepository.save(accountModelCredit);
				
				break;
			case DEBIT:
				if(amount > accountDetails.getAccountBalance()) {
					throw new IllegalArgumentException("Withdrawal amount greater than account balance!!");
				}
				//Insert Into Transaction Table
				TransactionModel transactionModelDebit = new TransactionModel();
				transactionModelDebit.setUserId(userId);
				transactionModelDebit.setAccountId(accountDetails.getId());
				transactionModelDebit.setTransactionType(TransactionType.DEBIT.name());
				transactionModelDebit.setTransactionAmount(amount);
				save = transactionRepository.save(transactionModelDebit);
				
				//Update Balance in UserAccount
				AccountModel accountModelDebit = new AccountModel();
				accountModelDebit.setId(accountDetails.getId());
				accountModelDebit.setUserId(userId);
				accountModelDebit.setAccountBalance(accountDetails.getAccountBalance() - amount);
				userAccountRepository.save(accountModelDebit);
				
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + transactionType);
		}
		return save;
	}
	
	@Override
	public List<TransactionModel> fetchTransactionHistory(Long userId) {
		return transactionRepository.findAllByUserId(userId);
	}
	
}
