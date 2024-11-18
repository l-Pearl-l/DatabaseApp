package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.tables.ClassRoom;

import java.sql.SQLException;

public class ClassRoomModelTable {
    private static final TableView<ClassRoom> table = new TableView<>();
    private static final TableColumn<ClassRoom, Integer> columnCode = new TableColumn<>("Код класса");
    private static final TableColumn<ClassRoom, String> columnClassName = new TableColumn<>("Название класса");
    private static final TableColumn<ClassRoom, Double> columnPrice = new TableColumn<>("Цена");


    public static void attachColumns(){
        columnCode.setCellValueFactory(new PropertyValueFactory<>("classRoomCode"));
        columnClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(columnCode, columnClassName, columnPrice);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public static void setDataInCell(){
            try {
                while(ClassRoom.getDataFromDB().next()){
                    table.getItems().add(new ClassRoom(ClassRoom.getDataFromDB().getInt("room_class_code"),
                                                       ClassRoom.getDataFromDB().getString("class_name"),
                                                       ClassRoom.getDataFromDB().getDouble("price")));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public static TableView<ClassRoom> buildTable(){
        attachColumns();
        setDataInCell();
        return table;
    }
}
