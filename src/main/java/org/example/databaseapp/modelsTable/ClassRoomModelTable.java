package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.tables.ClassRoom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassRoomModelTable{
    private static final TableView<ClassRoom> table = new TableView<>();
    private static final TableColumn<ClassRoom, Integer> columnCode = new TableColumn<>("Код класса");
    private static final TableColumn<ClassRoom, String> columnClassName = new TableColumn<>("Название класса");
    private static final TableColumn<ClassRoom, Double> columnPrice = new TableColumn<>("Цена");
    private static final ResultSet dataDB = ClassRoom.getDataFromDB();

    private static void attachColumns(){
        columnCode.setCellValueFactory(new PropertyValueFactory<>("classRoomCode"));
        columnClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(columnCode, columnClassName, columnPrice);
    }

    private static void setDataInCell(){
            try {
                while(dataDB.next()){
                    table.getItems().add(new ClassRoom(dataDB.getInt("room_class_code"),
                                                       dataDB.getString("class_name"),
                                                       dataDB.getDouble("price")));
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

    public static TableView<ClassRoom> getTable(){
        return table;
    }
}
