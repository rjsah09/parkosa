package com.parkosa.dto;

import java.time.LocalDateTime;

public class InsertReservationDTO {
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int totalAmount;
	private String carCode;
	private int parkingSpaceId;
	private int parkingLotId;
	
	public InsertReservationDTO(LocalDateTime startTime, LocalDateTime endTime, int totalAmount, String carCode,
			int parkingSpaceId, int parkingLotId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalAmount = totalAmount;
		this.carCode = carCode;
		this.parkingSpaceId = parkingSpaceId;
		this.parkingLotId = parkingLotId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	public int getTotalAmount() {
		return totalAmount;
	}

	public String getCarCode() {
		return carCode;
	}

	public int getParkingSpaceId() {
		return parkingSpaceId;
	}

	public int getParkingLotId() {
		return parkingLotId;
	}
	
}
