package com.bank.home.dto;

import com.bank.home.constants.ExceptionConstant;
import com.bank.home.constants.ResponseCode;
import lombok.Data;

@Data
public class CommonResponseDTO {
	
	private String code;
	
	private String message;
	
	private Object payload;
	
	public CommonResponseDTO(String code, String message, Object payload) {
		this.code = code;
		this.message = message;
		this.payload = payload;
	}
	
	public CommonResponseDTO() {
	}
	
	public static CommonResponseDTO getNoDataFoundResponse() {
		return new CommonResponseDTO(ResponseCode.NO_DATA_FOUND, ExceptionConstant.NO_DATA_FOUND, null);
	}
	
	public static CommonResponseDTO getNoDataFoundResponse(String message) {
		return new CommonResponseDTO(ResponseCode.NO_DATA_FOUND, message, null);
	}
	
	public static CommonResponseDTO successInstance(Object payload) {
		return new CommonResponseDTO(ResponseCode.SUCCESS, "Success", payload);
	}
	
	public static CommonResponseDTO successInstance() {
		return new CommonResponseDTO(ResponseCode.SUCCESS, "Success", null);
	}
	
}
