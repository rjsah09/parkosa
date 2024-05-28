package com.parkosa.dto;

public class InsertParkingLotDTO {
	private String telNumber;
	private String name;
	private int locationId;
	
	public InsertParkingLotDTO(String telNumber, String name, int locationId) {
		this.telNumber = telNumber;
		this.name = name;
		this.locationId = locationId;
	}
	
	public String getTelNumber() {
		return telNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
}
