package com.parkosa.dto;

public class InsertFeePolicyDTO {
	
	private int id;
	private int increaseMinute;
	private int increaseFee;
	private int maximumTime;
	private int carTypeId;
	private int parkingLotId;
	
	public InsertFeePolicyDTO(int increaseMinute, int increaseFee, int maximumTime, int carTypeId, int parkingLotId) {
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeId = carTypeId;
		this.parkingLotId = parkingLotId;
	}
	
	public int getIncreaseMinute() {
		return increaseMinute;
	}
	
	public int getIncreaseFee() {
		return increaseFee;
	}
	
	public int getMaximumTime() {
		return increaseFee;
	}

	
	public int getCarTypeId() {
		return carTypeId;
	}
	
	public int getParkingLotId() {
		return parkingLotId;
	}
}
