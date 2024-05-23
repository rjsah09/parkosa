package com.parkosa.dao;

import java.time.LocalDate;


public class AccountDAO {
	
	private String phoneNumber;
	private String password;
	private String name;
	private String eMail;
	private LocalDate registerDate;
	
	//생성자
	public AccountDAO(String phoneNumber, String password,
			String name, String eMail, LocalDate registerDate) {
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.name = name;
		this.eMail = eMail;
		this.registerDate = registerDate;
	}

	//Getters and Setters
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	
}
