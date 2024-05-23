package com.parkosa.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationVO {
	
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalAmount;
    private String carCode;
    private int parkingSpaceId;
    private int ParkingLotId;
    
	public ReservationVO(String id, LocalDateTime startTime, LocalDateTime endTime, int totalAmount, String carCode,
			int parkingSpaceId, int parkingLotId) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalAmount = totalAmount;
		this.carCode = carCode;
		this.parkingSpaceId = parkingSpaceId;
		ParkingLotId = parkingLotId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
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
		return ParkingLotId;
	}

	public void setParkingLotId(int parkingLotId) {
		ParkingLotId = parkingLotId;
	}

    
    
}
