package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;
import org.example.databaseapp.tables.ClassRoom;
import org.example.databaseapp.tables.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerModelTable {
    private static final HBox panelInsert = new HBox();
    private static final TextField idCustomer = GUI.createTextField("id");
    private static final TextField fio = GUI.createTextField("ФИО");
    private static final TextField dataPassport = GUI.createTextField("Данные паспорта");
    private static final TextField dateArrival = GUI.createTextField("Дата прибытия");
    private static final TextField dateDeparture = GUI.createTextField("Дата выезда");
    private static final String nameTable = "customer";
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

    public static String getNameTable(){
        return CustomerModelTable.nameTable;
    }

    public static void delete(){
        Customer selectedId = table.getSelectionModel().getSelectedItem();
        int id = selectedId.getCustomerId();
        try {
            Database.makeQueryDelete(nameTable, "customer_id", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(idCustomer, fio, dataPassport, dateArrival, dateDeparture);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("customer", Integer.parseInt(idCustomer.getText()),
                fio.getText(), dataPassport.getText(), dateArrival.getText(), dateDeparture.getText());
    }

    public static void clearColumns(){
        table.getColumns().clear();
    }
}
