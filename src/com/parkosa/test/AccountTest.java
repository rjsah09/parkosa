package com.parkosa.test;

import com.parkosa.dao.ParkingLotDAO;
import com.parkosa.dto.InsertParkingLotDTO;

public class AccountTest {
	int a = 1;
	public static void main(String[] args) {
		System.out.println("g00d");
		ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
		InsertParkingLotDTO dto = new InsertParkingLotDTO("A주차장", "02-123-456", 40, "서울시 종로구 대명길40 주차타워", "이미지링크");
		parkingLotDAO.insertParkLot(dto);
	}
}
