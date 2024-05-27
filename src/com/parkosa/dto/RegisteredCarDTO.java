package com.parkosa.dto;

public class RegisteredCarDTO {
	private String carCode;
	private String carTypeName;
	
	public RegisteredCarDTO(String carCode, String carTypeName) {
		this.carCode = carCode;
		this.carTypeName = carTypeName;
	}
	
	public String getCarCode() {
		return carCode;
	}
	
	public String getCarTypeName() {
		return carTypeName;
	}
	
}
