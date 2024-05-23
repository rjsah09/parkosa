package com.parkosa.vo;

public class ReservationVO {
    private String id;
    private String startTime;
    private String endTime;
    private int totalAmount;
    private int parkingSpaceId;
    private int parikingSpaceId;
    private int ParkingLotId;

    public ReservationVO(String id, String startTime, String endTime, int totalAmount, int parkingSpaceId, int parikingSpaceId, int parkingLotId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalAmount = totalAmount;
        this.parkingSpaceId = parkingSpaceId;
        this.parikingSpaceId = parikingSpaceId;
        ParkingLotId = parkingLotId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public int getParikingSpaceId() {
        return parikingSpaceId;
    }

    public void setParikingSpaceId(int parikingSpaceId) {
        this.parikingSpaceId = parikingSpaceId;
    }

    public int getParkingLotId() {
        return ParkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        ParkingLotId = parkingLotId;
    }
}
