package com.parkosa.vo;

public class CarVO {
	
	private String code;
	private String accountPhoneNumber;
	private String carTypeId;
	
	public CarVO(String code, String accountPhoneNumber, String carTypeId) {
		this.code = code;
		this.accountPhoneNumber = accountPhoneNumber;
		this.carTypeId = carTypeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccountPhoneNumber() {
		return accountPhoneNumber;
	}

	public void setAccountPhoneNumber(String accountPhoneNumber) {
		this.accountPhoneNumber = accountPhoneNumber;
	}

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}
	
	
 	
}
