package com.parkosa.dto;

public class InsertParkingSpaceDTO {
	private int parkingLotId;
	private String description;
	private int feePolicyId;
	
	public InsertParkingSpaceDTO(int parkingLotId, String description, int feePolicyId) {
		this.parkingLotId = parkingLotId;
		this.description = description;
		this.feePolicyId = feePolicyId;
	}
	
	public int getParkingLotId() {
		return parkingLotId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getFeePolicyId() {
		return feePolicyId;
	}
	
}
