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
import org.example.databaseapp.tables.Service;

import java.sql.SQLException;

public class ServicesModelTable {
    private static final String nameTable = "services";
    private static final TableView<Service> table = new TableView<>();
    private static final TableColumn<Service, Integer> serviceCode = new TableColumn<>("Код услуги");
    private static final TableColumn<Service, String> serviceName = new TableColumn<>("Название услуги");
    private static final TableColumn<Service, Double> servicePrice = new TableColumn<>("Цена");
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField nameService = GUI.createTextField("Название услуги");
    private static final TextField price = GUI.createTextField("Цена");


    private static void attachColumn(){
        serviceCode.setCellValueFactory(new PropertyValueFactory<>("serviceCode"));
        serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        servicePrice.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(serviceCode, serviceName, servicePrice);
    }

    private static void setDataInCell(){
        try {
            while(Service.getDataFromDB().next()){
                table.getItems().add(new Service(Service.getDataFromDB().getInt("service_code"),
                        Service.getDataFromDB().getString("service_name"),
                        Service.getDataFromDB().getDouble("service_price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TableView<Service>buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }


    public static TableView<Service> getTable(){
        return table;
    }

    public static String getNameTable(){
        return ServicesModelTable.nameTable;
    }

    public static void delete(){
        Service selectedId = table.getSelectionModel().getSelectedItem();
        int id = selectedId.getServiceCode();
        try {
            Database.makeQueryDelete(nameTable, "service_code", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, nameService, price);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("services", Integer.parseInt(id.getText()), nameService.getText(), Double.parseDouble(price.getText()));
    }

    public static void clearColumns(){
        table.getColumns().clear();
    }
}
