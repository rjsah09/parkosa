package com.parkosa.vo;

public class ParkingLotVO {
	
	private int id;
	private String name;
	private String telNumber;
	private String address;
	private int availablePark;
	private int locationId;
	private String imageLink;

	public ParkingLotVO(int id, String name, String telNumber, String address, int availablePark, int locationId, String imageLink) {
		this.id = id;
		this.name = name;
		this.telNumber = telNumber;
		this.address = address;
		this.availablePark = availablePark;
		this.locationId = locationId;
		this.imageLink = imageLink;
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

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
}
