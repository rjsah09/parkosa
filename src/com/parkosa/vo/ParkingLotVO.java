package com.parkosa.vo;

public class ParkingLotVO {
	
	private int id;
	private String name;
	private String phoneNumber;
	private String addresss;
	private int availablePark;
	private int locationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public int getAvailablePark() {
		return availablePark;
	}

	public void setAvailablePark(int availablePark) {
		this.availablePark = availablePark;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
}
