package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.tables.ClassRoom;

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
        table.getItems().add(new ClassRoom(100, "fip", 800));
    }

    public static TableView<ClassRoom> buildTable(){
        attachColumns();
        setDataInCell();
        return table;
    }
}
