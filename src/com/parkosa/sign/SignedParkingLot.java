package com.parkosa.sign;

public class SignedParkingLot {
	
	private static SignedParkingLot parking_lot;
	private static int id;
	private static String name;
	private static String telNumber;
	private static String address;
	private static int locationId;
	private static String imageLink;
	
	public static SignedParkingLot parking_lot() {
		if (parking_lot == null) {
			parking_lot = new SignedParkingLot();
        }
        
        return parking_lot;
	}
	public static int getid() {
		return id;
	}
	
}
