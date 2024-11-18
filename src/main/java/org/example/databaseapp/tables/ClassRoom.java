package org.example.databaseapp.tables;

import org.example.databaseapp.Database;

import java.sql.ResultSet;

public class ClassRoom {
    private String nameTable = "room_class";
//    private static final String classRoomCode = "room_class_code";
//    private static final String className = "class_name";
//    private static final String price = "price";
    private int classRoomCode;
    private String className;
    private double price;

    public ClassRoom(){
        this.classRoomCode = 0;
        this.className = "";
        this.price = 0.0;
    }

    public ClassRoom(int classRoomCode, String className, double price){
        this.classRoomCode = classRoomCode;
        this.className = className;
        this.price = price;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(int classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
