package com.parkosa.dto;

import java.time.LocalDateTime;

public class RegisteredReservationDTO {
	private int reservationId;
	private String parkingLotname;
	private String parkingSpaceDescription;
	private String startTime;
	private String endTime;
	private int totalAmount;
	
	public RegisteredReservationDTO(int reservationId, String parkingLotname, String parkingSpaceDescription, String startTime,
			String endTime, int totalAmount) {
		this.reservationId = reservationId;
		this.parkingLotname = parkingLotname;
		this.parkingSpaceDescription = parkingSpaceDescription;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalAmount = totalAmount;
	}
	
	public int getReservationId() {
		return reservationId;
	}

	public String getParkingLotname() {
		return parkingLotname;
	}


	public String getParkingSpaceDescription() {
		return parkingSpaceDescription;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

}
