package com.parkosa.vo;

public class ParkingSpaceVO {
	
	private int id;
	private int parkingLotId;
	private String description;
	private int feePolicyId;
	
	public ParkingSpaceVO(int id, int parkingLotId, String description, int feePolicyId) {
		this.id = id;
		this.parkingLotId = parkingLotId;
		this.description = description;
		this.feePolicyId = feePolicyId;
	}

	public ParkingSpaceVO(int parkingLotId, String description, int feePolicyId) {
		this.parkingLotId = parkingLotId;
		this.description = description;
		this.feePolicyId = feePolicyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFeePolicyId() {
		return feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}
	
}
