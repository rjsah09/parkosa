package com.parkosa.vo;

public class FeePolicyVO {
	
	private int id;
	private int increaseMinute;
	private int increaseFee;
	private int maximumTime;
	private int cartypeId;
	private int parkinglotId;
	
	public FeePolicyVO(int id, 
						int increaseMinute,
						int increaseFee,
						int maximumTime,
						int cartypeId,
						int parkinglotId){
		this.id = id;
		this.increaseMinute = increaseMinute;
		this.increaseFee = increaseFee;
		this.maximumTime = maximumTime;
		this.cartypeId = cartypeId;
		this.parkinglotId = parkinglotId;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getincreaseMinute() {
		return increaseMinute;
	}

	public void setincreaseMinute(int increaseMinute) {
		this.increaseMinute = increaseMinute;
	}
	
	public int getincreaseFee() {
		return increaseFee;
	}

	public void setincreaseFee(int increaseFee) {
		this.increaseFee = increaseFee;
	}
	
	public int getmaximumTime() {
		return maximumTime;
	}

	public void setmaximumTime(int maximumTime) {
		this.maximumTime = maximumTime;
	}
	
	public int getcartypeId() {
		return cartypeId;
	}

	public void setcartypeId(int cartypeId) {
		this.cartypeId = cartypeId;
	}
	
	public int getparkinglotId() {
		return parkinglotId;
	}

	public void setparkinglotId(int parkinglotId) {
		this.parkinglotId = parkinglotId;
	}
}

