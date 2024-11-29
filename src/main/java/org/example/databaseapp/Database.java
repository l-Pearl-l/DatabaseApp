package org.example.databaseapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

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

    public static void makeQueryDelete(String nameTable, String designationIdTable, int id) throws SQLException {
        final String QUERY_DELETE = "DELETE FROM " + nameTable + " WHERE " + designationIdTable + " = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(QUERY_DELETE);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public static ResultSet makeQuerySelectAll(String nameTable){
        final String QUERY = "SELECT * " + " FROM " + nameTable;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void makeQueryInsert(String nameTable, Object... values){
        StringBuilder query = new StringBuilder("INSERT INTO " + nameTable + " VALUES (");
        for(int i = 0; i < values.length; i++){
            query.append("?, ");
        }

        query.setLength(query.length() - 2);
        query.append(")");
        try(PreparedStatement preparedStatement = connect.prepareStatement(query.toString())) {
            for(int i = 0; i < values.length; i++){
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeQueryUpdate(String nameTable, Map<String, Object> data, String nameId, int id){
        StringBuilder query = new StringBuilder("UPDATE " + nameTable + " SET ");
        Set<String> keys = data.keySet();
        Collection<Object> values = data.values();
        Iterator<String> iteratorKeys = keys.iterator();
        Iterator<Object> iteratorValues = values.iterator();
        for(int i = 0; i < data.size(); i++){
            Object element = iteratorKeys.next();
            query.append(element + " = ?, ");
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE " + nameId + " = " + id);
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query.toString());
            for(int i = 0; i < data.size(); i++){
                preparedStatement.setObject(i + 1, iteratorValues.next());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
