package org.example.databaseapp.tables;

import org.example.databaseapp.Database;

import java.sql.ResultSet;

public class Customer {
    private static final String nameTable = "customer";
    private int customerId;
    private String fio;
    private String dataPassport;
    private String dateArrival;
    private String dateDeparture;
    private static final ResultSet dataFromDB = Database.makeQuerySelectAll(nameTable);

    public Customer(int customerId, String fio, String dataPassport, String dateArrival, String dateDeparture) {
        this.customerId = customerId;
        this.fio = fio;
        this.dataPassport = dataPassport;
        this.dateArrival = dateArrival;
        this.dateDeparture = dateDeparture;
    }

    public static ResultSet getDataFromDB(){
        return dataFromDB;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDataPassport() {
        return dataPassport;
    }

    public void setDataPassport(String dataPassport) {
        this.dataPassport = dataPassport;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }
}
