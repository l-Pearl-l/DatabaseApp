package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.Database;
import org.example.databaseapp.tables.ClassRoom;
import org.example.databaseapp.tables.HotelRoom;

import java.sql.ResultSet;
import java.sql.SQLException;

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
                                                    dataDB.getInt("additional_services"),
                                                    dataDB.getInt("floor"),
                                                    dataDB.getDouble("price"),
                                                    dataDB.getString("busy")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public static void clearColumns(){
        table.getColumns().clear();
    }
}
