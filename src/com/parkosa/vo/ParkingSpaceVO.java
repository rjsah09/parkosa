package com.parkosa.vo;

public class ParkingSpaceVO {
	
	private int id;
	private int parkinglotId;
	private String decription;
	private int feepolicyId;

	
	public ParkingSpaceVO(int id, 
						int parkinglotId,
						String decription,
						int feepolicyId){
		this.id = id;
		this.parkinglotId = parkinglotId;
		this.decription = decription;
		this.feepolicyId = feepolicyId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getparkinglotId() {
		return parkinglotId;
	}

	public void setparkinglotId(int parkinglotId) {
		this.parkinglotId = parkinglotId;
	}
	
	public int getdecription() {
		return decription;
	}

	public void setdecription(String decription) {
		this.decription = decription;
	}
	
	public int getfeepolicyId() {
		return feepolicyId;
	}

	public void setfeepolicyId(int feepolicyId) {
		this.feepolicyId = feepolicyId;
	}

}
