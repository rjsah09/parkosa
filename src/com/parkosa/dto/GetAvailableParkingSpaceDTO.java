package com.parkosa.dto;

import oracle.net.aso.r;

public class GetAvailableParkingSpaceDTO {

	private int parkingLotId;
    private String parkingLotName;
    private String locationName;
    private String parkingSpaceDescription;
    private int predictPrice;

    public GetAvailableParkingSpaceDTO(int parkingLotId, String parkingLotName, String locationName, String parkingSpaceDescription, int predictPrice) {
    	this.parkingLotId = parkingLotId;
        this.parkingLotName = parkingLotName;
        this.locationName = locationName;
        this.parkingSpaceDescription = parkingSpaceDescription;
        this.predictPrice = predictPrice;
    }
    
    public int getParkingLotId() {
    	return parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getParkingSpaceDescription() {
        return parkingSpaceDescription;
    }

    public void setParkingSpaceDescription(String parkingSpaceDescription) {
        this.parkingSpaceDescription = parkingSpaceDescription;
    }

    public int getPredictPrice() {
        return predictPrice;
    }

    public void setPredictPrice(int predictPrice) {
        this.predictPrice = predictPrice;
    }
}
