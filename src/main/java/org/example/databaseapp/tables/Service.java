package org.example.databaseapp.tables;

import org.example.databaseapp.Database;

import java.sql.ResultSet;

public class Service {
    private static final  String nameTable = "services";
    private int serviceCode;
    private String serviceName;
    private double servicePrice;
    private static ResultSet dataFromDB = Database.makeQuerySelectAll(nameTable);


    public Service(int serviceCode, String serviceName, double servicePrice){
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public static ResultSet getDataFromDB(){
        return dataFromDB;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public static void setDataFromDB(ResultSet dataFromDB) {
        Service.dataFromDB = dataFromDB;
    }
}
