package com.parkosa.vo;

public class FeePolicyVO {
	
	private int id;
	private int increaseMinute;
	private int increaseFee;
	private int maximumTime;
	private int carTypeId;
	private int parkingLotId;
	
	public FeePolicyVO(int id, int increaseMinute, int increaseFee, int maximumTime, int carTypeId, int parkingLotId) {
		super();
		this.id = id;
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.carTypeId = carTypeId;
		this.parkingLotId = parkingLotId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIncreaseMinute() {
		return increaseMinute;
	}

	public void setIncreaseMinute(int increaseMinute) {
		this.increaseMinute = increaseMinute;
	}

	public int getIncreaseFee() {
		return increaseFee;
	}

	public void setIncreaseFee(int increaseFee) {
		this.increaseFee = increaseFee;
	}

	public int getMaximumTime() {
		return maximumTime;
	}

	public void setMaximumTime(int maximumTime) {
		this.maximumTime = maximumTime;
	}

	public int getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}

	public int getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	
	
}

