package com.parkosa.dto;

public class GetAvailableParkingSpaceDTO {

    private String parkingLotName;
    private String locationName;
    private String parkingSpaceDescription;
    private int predictPrice;

    public GetAvailableParkingSpaceDTO(String parkingLotName, String locationName, String parkingSpaceDescription, int predictPrice) {
        this.parkingLotName = parkingLotName;
        this.locationName = locationName;
        this.parkingSpaceDescription = parkingSpaceDescription;
        this.predictPrice = predictPrice;
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
