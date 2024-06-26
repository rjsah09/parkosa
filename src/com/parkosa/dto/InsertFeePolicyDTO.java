package com.parkosa.dto;

public class InsertFeePolicyDTO {
	
	private int id;
	private int increaseMinute;
	private int increaseFee;
	private int maximumTime;
	private int carTypeId;
	private int parkingLotId;
	private String carTypeName;
	
	public InsertFeePolicyDTO(int id, int increaseMinute, int increaseFee, int maximumTime, int carTypeId,
			int parkingLotId, String carTypeName) {
		this.id = id;
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeId = carTypeId;
		this.parkingLotId = parkingLotId;
		this.carTypeName = carTypeName;
	}

	public InsertFeePolicyDTO(int increaseMinute, int increaseFee, int maximumTime, int carTypeId, int parkingLotId) {
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeId = carTypeId;
		this.parkingLotId = parkingLotId;
	}

	public InsertFeePolicyDTO(int increaseMinute, int increaseFee, int maximumTime, String carTypeName) {
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeName = carTypeName;
	}
	
	public InsertFeePolicyDTO(int id, int increaseMinute, int increaseFee, int maximumTime, String carTypeName) {
		this.id = id;
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeName = carTypeName;
	}
	
	public int getId() {
		return id;
	}

	public int getIncreaseMinute() {
		return increaseMinute;
	}

	public int getIncreaseFee() {
		return increaseFee;
	}

	public int getMaximumTime() {
		return maximumTime;
	}

	public int getCarTypeId() {
		return carTypeId;
	}

	public int getParkingLotId() {
		return parkingLotId;
	}

	public String getCarTypeName() {
		return carTypeName;
	}
}
