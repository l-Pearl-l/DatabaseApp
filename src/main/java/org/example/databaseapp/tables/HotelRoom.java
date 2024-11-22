package org.example.databaseapp.tables;

import org.example.databaseapp.Database;

import java.sql.ResultSet;

public class HotelRoom {
    private static final String nameTable = "hotel_room";
    private int hotelRoomId;
    private int customerId;
    private int numberOfPeople;
    private String roomClass;
    private int additionalServices;
    private int floor;
    private double price;
    private String busy;
    private static final ResultSet dataFromDB = Database.makeQuerySelectAll(nameTable);

    public HotelRoom(int hotelRoomId, int customerId, int numberOfPeople, String roomClass, int additionalServices, int floor, double price, String busy) {
        this.hotelRoomId = hotelRoomId;
        this.customerId = customerId;
        this.numberOfPeople = numberOfPeople;
        this.roomClass = roomClass;
        this.additionalServices = additionalServices;
        this.floor = floor;
        this.price = price;
        this.busy = busy;
    }

    public static ResultSet getDataFromDB(){
        return dataFromDB;
    }

    public int getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public int getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(int additionalServices) {
        this.additionalServices = additionalServices;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBusy() {
        return busy;
    }

    public void setBusy(String busy) {
        this.busy = busy;
    }
}
