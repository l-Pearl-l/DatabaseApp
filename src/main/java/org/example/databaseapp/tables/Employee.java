package org.example.databaseapp.tables;

import org.example.databaseapp.Database;

import java.sql.ResultSet;

public class Employee {
    static final String nameTable = "employee";
    int employeeId;
    String fio;
    int postCode;
    String phoneNumber;
    String education;
    String additionalLanguage;
    private static final ResultSet dataFromDB = Database.makeQuerySelectAll(nameTable);

    public Employee(int employeeId, String fio, int postCode, String phoneNumber, String education, String additionalLanguage) {
        this.employeeId = employeeId;
        this.fio = fio;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.education = education;
        this.additionalLanguage = additionalLanguage;
    }

    public static ResultSet getDataFromDB(){
        return dataFromDB;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAdditionalLanguage() {
        return additionalLanguage;
    }

    public void setAdditionalLanguage(String additionalLanguage) {
        this.additionalLanguage = additionalLanguage;
    }
}
