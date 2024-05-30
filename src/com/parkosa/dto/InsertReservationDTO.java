package com.parkosa.dto;

public class InsertReservationDTO {
	
	private String startTime;
	private String endTime;
	private int totalAmount;
	private String carCode;
	private int parkingSpaceId;
	private int parkingLotId;

	public InsertReservationDTO(String startTime, String endTime, String carCode, int parkingSpaceId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.carCode = carCode;
		this.parkingSpaceId = parkingSpaceId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public int getParkingSpaceId() {
		return parkingSpaceId;
	}

	public void setParkingSpaceId(int parkingSpaceId) {
		this.parkingSpaceId = parkingSpaceId;
	}

	public int getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
}
