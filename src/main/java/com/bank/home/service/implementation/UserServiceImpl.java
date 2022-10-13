package com.bank.home.service.implementation;

import com.bank.home.constants.ResponseMessage;
import com.bank.home.model.AccountModel;
import com.bank.home.model.UserModel;
import com.bank.home.repository.UserAccountRepository;
import com.bank.home.repository.UserRepository;
import com.bank.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Override
	public Optional<UserModel> userLogin(String email, String password) {
		return userRepository.findByUserEmailAndUserPassword(email, password);
//		if(user.isPresent()) {
//			return user;
//		}
//		return user;
	}
	
	@Override
	public String createUser(UserModel userModel) {
		Optional<UserModel> byUserEmail = userRepository.findByUserEmail(userModel.getUserEmail());
		if(byUserEmail.isPresent()){
			return ResponseMessage.USER_EMAIL_ALREADY_EXISTS;
		}
		Optional<UserModel> byUserName = userRepository.findByUserName(userModel.getUserName());
		if(byUserName.isPresent()){
			return ResponseMessage.USER_NAME_ALREADY_EXISTS;
		}
		
		UserModel save = userRepository.save(userModel);
		if(save.getId()>0){
			AccountModel newAccountModel= new AccountModel();
			newAccountModel.setAccountBalance(0L);
			newAccountModel.setUserId(save.getId());
			userAccountRepository.save(newAccountModel);
			return ResponseMessage.USER_REGISTRATION_SUCCESSFUL;
		}
		
		return ResponseMessage.USER_REGISTRATION_FAILED;
	}
}
