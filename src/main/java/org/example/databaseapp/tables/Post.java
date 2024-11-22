package org.example.databaseapp.tables;

import org.example.databaseapp.Database;

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

    public static void setNameTable(String nameTable) {
        Post.nameTable = nameTable;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static ResultSet getDataFromDB() {
        return dataFromDB;
    }

    public void setDataFromDB(ResultSet dataFromDB) {
        this.dataFromDB = dataFromDB;
    }
}
