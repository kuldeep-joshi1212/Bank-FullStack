package com.bank.home.controller;

import com.bank.home.constants.ResponseCode;
import com.bank.home.constants.ResponseMessage;
import com.bank.home.dto.CommonResponseDTO;
import com.bank.home.enums.TransactionType;
import com.bank.home.model.AccountModel;
import com.bank.home.model.TransactionModel;
import com.bank.home.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transaction/balance")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDTO> fetchBalance(@PathVariable Long userId) {
		CommonResponseDTO commonResponseDTO;
		AccountModel accountBalance = transactionService.fetchBalance(userId);
		commonResponseDTO = new CommonResponseDTO(ResponseCode.SUCCESS, ResponseCode.SUCCESS, accountBalance);
		return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{transactionType}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDTO> depositAmountInWallet(@PathVariable Long userId,
		@PathVariable String transactionType, @RequestParam(name = "amount") Long amount) {
		TransactionType transactionTypes = TransactionType.valueOf(transactionType.toUpperCase());
		CommonResponseDTO commonResponseDTO;
		TransactionModel transactionModel = transactionService.amountTransaction(transactionTypes, userId, amount);
		switch(transactionTypes) {
			case CREDIT:
				commonResponseDTO = new CommonResponseDTO(ResponseCode.SUCCESS,
					ResponseMessage.AMOUNT_CREDITED_SUCCESSFULLY, transactionModel);
				break;
			case DEBIT:
				commonResponseDTO = new CommonResponseDTO(ResponseCode.SUCCESS,
					ResponseMessage.AMOUNT_DEBITED_SUCCESSFULLY, transactionModel);
				break;
			default:
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDTO> fetchTransactionHistory(@PathVariable Long userId){
		CommonResponseDTO commonResponseDTO;
		List<TransactionModel> transactionModels = transactionService.fetchTransactionHistory(userId);
		commonResponseDTO=new CommonResponseDTO(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, transactionModels);
		return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
	}
	
}
