package org.example.databaseapp.tables;

import org.example.databaseapp.Database;
import org.example.databaseapp.modelsTable.ClassRoomModelTable;

import java.sql.ResultSet;

public class Post {
    private static String nameTable = "posts";
    private int postCode;
    private String postName;
    private double salary;
    private static ResultSet dataFromDB = Database.makeQuerySelectAll(nameTable);

    public Post(int postCode, String postName, double salary){
        this.postCode = postCode;
        this.postName = postName;
        this.salary = salary;
    }

    public static String getNameTable() {
        return nameTable;
    }

    public int getPostCode() {
        return postCode;
    }


    public String getPostName() {
        return postName;
    }


    public double getSalary() {
        return salary;
    }


    public static ResultSet getDataFromDB() {
        return dataFromDB;
    }

}
