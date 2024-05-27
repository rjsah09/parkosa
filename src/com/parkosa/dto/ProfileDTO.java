package com.parkosa.dto;

public class ProfileDTO {
	
	private String name;
	private String carCode;
	
	public ProfileDTO(String name, String carCode) {
		this.name = name;
		this.carCode = carCode;
	}

	private String getName() {
		return name;
	}
	
	private String getCarCode() {
		return carCode;
	}

}
