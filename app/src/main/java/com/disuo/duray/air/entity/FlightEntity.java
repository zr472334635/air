package com.disuo.duray.air.entity;

public class FlightEntity {
    String PLTNo;
    String FlightDate;
    String StartTime;
    String EndTime;
    String StartPlace;
    String EndPlace;
    String price;
    String Requirement;
    String SeatNum;

    public FlightEntity(String StartPlace, String EndPlace, String StartTime, String EndTime, String price) {
        this.StartPlace=StartPlace;
        this.EndPlace=EndPlace;
        this.StartTime=StartTime;
        this.EndTime=EndTime;
        this.price=price;
    }

    public String getPLTNo() {
        return PLTNo;
    }

    public void setPLTNo(String PLTNo) {
        this.PLTNo = PLTNo;
    }

    public String getFlightDate() {
        return FlightDate;
    }

    public void setFlightDate(String flightDate) {
        FlightDate = flightDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartPlace() {
        return StartPlace;
    }

    public void setStartPlace(String startPlace) {
        StartPlace = startPlace;
    }

    public String getEndPlace() {
        return EndPlace;
    }

    public void setEndPlace(String endPlace) {
        EndPlace = endPlace;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRequirement() {
        return Requirement;
    }

    public void setRequirement(String requirement) {
        Requirement = requirement;
    }

    public String getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(String seatNum) {
        SeatNum = seatNum;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "PLTNo='" + PLTNo + '\'' +
                ", FlightDate='" + FlightDate + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", StartPlace='" + StartPlace + '\'' +
                ", EndPlace='" + EndPlace + '\'' +
                ", price='" + price + '\'' +
                ", Requirement='" + Requirement + '\'' +
                ", SeatNum='" + SeatNum + '\'' +
                '}';
    }
}
