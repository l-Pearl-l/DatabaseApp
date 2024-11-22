package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.tables.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerModelTable {
    private static final TableView<Customer> table = new TableView<>();
    private static final TableColumn<Customer, Integer> customerIdColumn = new TableColumn<>("Id гостя");
    private static final TableColumn<Customer, String> fioColumn = new TableColumn<>("Фамилия, имя, отчество");
    private static final TableColumn<Customer, String> dataPassportColumn = new TableColumn<>("Данные паспорта");
    private static final TableColumn<Customer, String> dateArrivalColumn = new TableColumn<>("Дата прибытия");
    private static final TableColumn<Customer, String> dateDepartureColumn = new TableColumn<>("Дата выезда");
    private static final ResultSet dataDB = Customer.getDataFromDB();

    private static void attachColumn() {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        dataPassportColumn.setCellValueFactory(new PropertyValueFactory<>("dataPassport"));
        dateArrivalColumn.setCellValueFactory(new PropertyValueFactory<>("dateArrival"));
        dateDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("dateDeparture"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(customerIdColumn, fioColumn, dataPassportColumn,
                                  dateArrivalColumn, dateDepartureColumn);
    }

    private static void setDataInCell() {
        try {
            while (dataDB.next()) {
                table.getItems().add(new Customer(dataDB.getInt("customer_id"),
                                                  dataDB.getString("fio"),
                                                  dataDB.getString("data_passport"),
                                                  dataDB.getString("date_arrival"),
                                                  dataDB.getString("date_departure")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TableView<Customer> buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }

    public static TableView<Customer> getTable(){
        return table;
    }
}
