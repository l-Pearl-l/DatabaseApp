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
import org.example.databaseapp.tables.HotelRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HotelRoomModelTable {
    private static final String nameTable = "hotel_room";
    private static final TableView<HotelRoom> table = new TableView<>();
    private static final TableColumn<HotelRoom, Integer> hotelRoomIdColumn = new TableColumn<>("Id номера");
    private static final TableColumn<HotelRoom, Integer> customerIdColumn = new TableColumn<>("Гость");
    private static final TableColumn<HotelRoom, Integer> numberOfPeopleColumn = new TableColumn<>("Количество человек");
    private static final TableColumn<HotelRoom, Integer> roomClassColumn = new TableColumn<>("Класс номера");
    private static final TableColumn<HotelRoom, Integer> additionalServicesColumn = new TableColumn<>("Услуга");
    private static final TableColumn<HotelRoom, Integer> floorColumn = new TableColumn<>("Этаж");
    private static final TableColumn<HotelRoom, Double> priceColumn = new TableColumn<>("Цена");
    private static final TableColumn<HotelRoom, String> busyColumn = new TableColumn<>("Занят или нет");
    private static final ResultSet dataDB = HotelRoom.getDataFromDB();
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField idCustomer = GUI.createTextField("id гостя");
    private static final TextField numberOfPeople = GUI.createTextField("Количество людей");
    private static final TextField idClassRoom = GUI.createTextField("id класса номера");
    private static final TextField additionalService = GUI.createTextField("Услуга");
    private static final TextField floor = GUI.createTextField("Этаж");
    private static final TextField price = GUI.createTextField("Цена");
    private static final TextField busy = GUI.createTextField("Занятость");
    private static final Map<String, Object> dataUpdate = new LinkedHashMap<>();

    private static void attachColumn(){
        hotelRoomIdColumn.setCellValueFactory(new PropertyValueFactory<>("hotelRoomId"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        numberOfPeopleColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));
        roomClassColumn.setCellValueFactory(new PropertyValueFactory<>("roomClass"));
        additionalServicesColumn.setCellValueFactory(new PropertyValueFactory<>("additionalServices"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        busyColumn.setCellValueFactory(new PropertyValueFactory<>("busy"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(hotelRoomIdColumn, customerIdColumn, numberOfPeopleColumn, roomClassColumn,
                                    additionalServicesColumn, floorColumn, priceColumn, busyColumn);
    }

    private static void setDataInCell(){
        try {
            while(dataDB.next()){
                table.getItems().add(new HotelRoom( dataDB.getInt("hotel_room_id"),
                                                    dataDB.getInt("customer_id"),
                                                    dataDB.getInt("number_of_people"),
                                                    dataDB.getString("room_class"),
                                                    dataDB.getInt("service"),
                                                    dataDB.getInt("floor"),
                                                    dataDB.getDouble("price"),
                                                    dataDB.getString("busy")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillDataUpdate(){
        dataUpdate.put("number_of_people", Integer.parseInt(numberOfPeople.getText()));
        dataUpdate.put("room_class", Integer.parseInt(idClassRoom.getText()));
        dataUpdate.put("floor", Integer.parseInt(floor.getText()));
        dataUpdate.put("price", Double.parseDouble(price.getText()));
        dataUpdate.put("busy", busy.getText());
        dataUpdate.put("service", Integer.parseInt(additionalService.getText()));
        dataUpdate.put("customer_id", Integer.parseInt(idCustomer.getText()));
    }

    public static TableView<HotelRoom> buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }

    public static TableView<HotelRoom> getTable(){
        return table;
    }

    public static String getNameTable(){
        return HotelRoomModelTable.nameTable;
    }

    public static void delete(){
        HotelRoom selectedId = table.getSelectionModel().getSelectedItem();
        int id = selectedId.getHotelRoomId();
        try {
            Database.makeQueryDelete(nameTable, "hotel_room_id", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, idCustomer, numberOfPeople, idClassRoom, additionalService, floor, price, busy);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("hotel_room", Integer.parseInt(id.getText()),
                Integer.parseInt(numberOfPeople.getText()), Integer.parseInt(idClassRoom.getText()),
                Integer.parseInt(floor.getText()), Double.parseDouble(price.getText()), busy.getText(),
                Integer.parseInt(additionalService.getText()),
                Integer.parseInt(idCustomer.getText()));
    }

    public static void update(){
        fillDataUpdate();
        Database.makeQueryUpdate(nameTable, dataUpdate, "hotel_room_id", Integer.parseInt(id.getText()));
    }


    public static void clearColumns(){
        table.getColumns().clear();
    }
}
