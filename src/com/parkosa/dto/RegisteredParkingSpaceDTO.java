package com.parkosa.dto;

public class RegisteredParkingSpaceDTO {
	private String description;
	private int incereaseMinute;
	private int increaseFee;
	private int maximumTime;
	private String carTypeName;
	
	public RegisteredParkingSpaceDTO(String description,int incereaseMinute, int increaseFee, int maximumTime, String carTypeName) {
		this.description = description;
		this.incereaseMinute = incereaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeName = carTypeName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getIncereaseMinute() {
		return incereaseMinute;
	}

	public int getIncreaseFee() {
		return increaseFee;
	}

	public int getMaximumTime() {
		return maximumTime;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

}
