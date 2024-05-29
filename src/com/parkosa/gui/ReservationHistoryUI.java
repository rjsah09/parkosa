package com.parkosa.gui;

import java.util.List;

import com.parkosa.dao.ReservationDAO;
import com.parkosa.dto.RegisteredReservationDTO;

public class ReservationHistoryUI extends UI{

	@Override
	public void placeComponents() {
		// TODO Auto-generated method stub
		ReservationDAO dao = new ReservationDAO();
		List<RegisteredReservationDTO> list = dao.getReservations();
		
		System.out.println("UI도 돌아감");
		
		for (RegisteredReservationDTO dto: list) {
			System.out.println(dto.getParkingLotname());
			System.out.println(dto.getParkingSpaceDescription());
			System.out.println(dto.getStartTime());
			System.out.println(dto.getEndTime());
			System.out.println(dto.getTotalAmount());
		}
	}
	
}
