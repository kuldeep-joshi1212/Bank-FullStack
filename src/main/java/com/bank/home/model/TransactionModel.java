package com.bank.home.model;

import com.bank.home.entity.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;

@Component
@Data
@Entity
@Table(name = "user_transaction")
public class TransactionModel extends BaseEntity {
	
	@Column(name="userid", nullable = false)
	private Long userId;
	
	@Column(name="accountid", nullable = false)
	private Long accountId;
	
	@Column(name="transactiontype", nullable = false)
	private String transactionType;
	
	@Column(name="amount", nullable = false)
	private Long transactionAmount;
	
}
