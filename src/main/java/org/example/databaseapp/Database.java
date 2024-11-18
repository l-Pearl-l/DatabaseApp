package org.example.databaseapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {

    static Connection connect;
    static{
        try{
            connect = getConnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnect() throws SQLException {
        Properties dataConnect = new Properties();
        try(InputStream dataDB = new FileInputStream("src/main/resources/connectDB")){
            dataConnect.load(dataDB);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connect = DriverManager.getConnection(dataConnect.getProperty("url"),
                dataConnect.getProperty("login"),
                dataConnect.getProperty("password"));
        return connect;
    }

    public static void makeQueryDelete(String nameTable, String nameIdTable, int numberInTable) throws SQLException {
        final String QUERY_DELETE = "DELETE FROM " + nameTable + " WHERE " + nameIdTable + " = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(QUERY_DELETE);
        preparedStatement.setInt(1, numberInTable);
        preparedStatement.executeUpdate();
    }

    public static ResultSet makeQuerySelectColumn(String nameColumn, String nameTable){
        final String QUERY = "SELECT ? FROM ?" ;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connect.prepareStatement(QUERY);
            preparedStatement.setString(1, nameColumn);
            preparedStatement.setString(2, nameTable);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
