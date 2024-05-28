package com.parkosa.dto;

public class InsertParkingLotDTO {
	private String name;
	private String telNumber;
	private int locationId;
	private String address;
	private String imageLink;
	
	public InsertParkingLotDTO(String name, String telNumber, int locationId, String address, String imageLink) {
		this.telNumber = telNumber;
		this.name = name;
		this.locationId = locationId;
		this.address = address;
		this.imageLink = imageLink;
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
	
	public String getAddress() {
		return address;
	}
	
	public String getImageLink() {
		return imageLink;
	}
	
}
