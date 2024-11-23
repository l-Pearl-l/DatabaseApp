package org.example.databaseapp.tables;

import org.example.databaseapp.Database;
import org.example.databaseapp.modelsTable.ClassRoomModelTable;

import java.sql.ResultSet;

public class ClassRoom {
    private static final String nameTable = "room_class";
    private int classRoomCode;
    private String className;
    private double price;
    private static final ResultSet dataFromDB = Database.makeQuerySelectAll(nameTable);


    public ClassRoom(int classRoomCode, String className, double price){
        this.classRoomCode = classRoomCode;
        this.className = className;
        this.price = price;
    }


    public static ResultSet getDataFromDB() {
        return dataFromDB;
    }


    public static String getNameTable() {
        return ClassRoom.nameTable;
    }


    public int getClassRoomCode() {
        return classRoomCode;
    }


    public String getClassName() {
        return className;
    }


    public double getPrice() {
        return price;
    }
}
