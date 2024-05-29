package com.parkosa.dto;

import oracle.sql.DATE;

import java.sql.Date;

public class InsertReservationDTO {
	
	private Date startTime;
	private Date endTime;
	private int totalAmount;
	private String carCode;
	private int parkingSpaceId;
	private int parkingLotId;

	public InsertReservationDTO(Date startTime, Date endTime, String carCode, int parkingLotId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.carCode = carCode;
		this.parkingLotId = parkingLotId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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
