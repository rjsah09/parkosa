package com.parkosa.vo;

public class ParkingLotVO {
	
	private int id;
	private String name;
	private String phoneNumber;
	private String addresss;
	private boolean availablePark;
	private String locationId;
	
	public ParkingLotVO(int id, String name, String phoneNumber, String addresss, boolean availablePark,
			String locationId) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.addresss = addresss;
		this.availablePark = availablePark;
		this.locationId = locationId;
	}

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

	public boolean isAvailablePark() {
		return availablePark;
	}

	public void setAvailablePark(boolean availablePark) {
		this.availablePark = availablePark;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
}
