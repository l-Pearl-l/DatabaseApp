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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassRoomModelTable{
    private static final String nameTable = "room_class";
    private static final TableView<ClassRoom> table = new TableView<>();
    private static final TableColumn<ClassRoom, Integer> columnCode = new TableColumn<>("Код класса");
    private static final TableColumn<ClassRoom, String> columnClassName = new TableColumn<>("Название класса");
    private static final TableColumn<ClassRoom, Double> columnPrice = new TableColumn<>("Цена");
    private static final ResultSet dataDB = ClassRoom.getDataFromDB();
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField nameClass = GUI.createTextField("Название класса");
    private static final TextField price = GUI.createTextField("Цена");
    private static final  Map<String, Object> dataUpdate = new LinkedHashMap<>();


    public static String getNameTable(){
       return ClassRoomModelTable.nameTable;
    }

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

    private static void fillDataUpdate(){
        dataUpdate.put("class_name", nameClass.getText());
        dataUpdate.put("price", Double.parseDouble(price.getText()));
    }

    public static TableView<ClassRoom> buildTable(){
        attachColumns();
        setDataInCell();
        return table;
    }

    public static TableView<ClassRoom> getTable(){
        return table;
    }

    public static void delete(){
        ClassRoom selectedId = table.getSelectionModel().getSelectedItem();
        int id = selectedId.getClassRoomCode();
        try {
            Database.makeQueryDelete(nameTable, "room_class_code", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, nameClass, price);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("room_class", Integer.parseInt(id.getText()),
                nameClass.getText(),
                Double.parseDouble(price.getText()));
    }

    public static void update(){
        fillDataUpdate();
        Database.makeQueryUpdate("room_class", dataUpdate, "room_class_code", Integer.parseInt(id.getText()));
    }


    public static void clearColumns(){
        table.getColumns().clear();
    }

}
