package com.bank.home.service;

import com.bank.home.model.UserModel;
import org.apache.catalina.User;

import java.util.Optional;

public interface UserService {
	
	Optional<UserModel> userLogin(String email, String password);
	
	String createUser(UserModel userModel);
	
}
