package com.bank.home.controller;

import com.bank.home.constants.ResponseCode;
import com.bank.home.constants.ResponseMessage;
import com.bank.home.dto.CommonResponseDTO;
import com.bank.home.model.UserModel;
import com.bank.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDTO> loginUser(@RequestParam(name = "email") String email,
		@RequestParam(name = "password") String password) {
		CommonResponseDTO commonResponseDTO;
		
		Optional<UserModel> userModel = userService.userLogin(email, password);
		
		if(userModel.isPresent()) {
			commonResponseDTO = new CommonResponseDTO(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, userModel);
		} else {
			commonResponseDTO = new CommonResponseDTO().getNoDataFoundResponse(ResponseMessage.NO_USER_FOUND);
		}
		return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDTO> registerUser(@RequestBody UserModel userModel) {
		CommonResponseDTO commonResponseDTO;
		String userCreationStatus = userService.createUser(userModel);
		commonResponseDTO = new CommonResponseDTO().successInstance(userCreationStatus);
		return new ResponseEntity<>(commonResponseDTO, HttpStatus.CREATED);
	}
	
}
