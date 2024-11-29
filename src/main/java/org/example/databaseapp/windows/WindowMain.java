package org.example.databaseapp.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.databaseapp.GUI;
import org.example.databaseapp.modelsTable.*;
import org.example.databaseapp.panels.PanelSelected;


public class WindowMain {
    private static final Button selectionTable = GUI.createButton("Выбрать таблицу");
    private static final Button update = GUI.createButton("Обновить");
    private static final Button insert = GUI.createButton("Вставить");
    private static final Button delete = GUI.createButton("Удалить");
    private static final HBox panelSelection = new HBox();
    private static final HBox panelInsert = new HBox();
    private static final VBox positionTable = new VBox();
    private static String nameTable;

    public static void setNameTable(String nameTable) {
        WindowMain.nameTable = nameTable;
    }

    static{
        selectionTable.setOnAction(e -> {
            panelSelection.getChildren().clear();
            panelSelection.getChildren().add(PanelSelected.createPanel());
        });
        delete.setOnAction(e -> {
           delete();
        });
        insert.setOnAction(e -> {
            insert();
        });
        update.setOnAction(e -> {
            update();
        });
    }

    public static void createWindow(Stage stage){
        HBox panel = new HBox(10, selectionTable, update, insert, delete);
        VBox positionPanel = new VBox();

        positionPanel.getChildren().addAll(panel, panelSelection, panelInsert);
        BorderPane positions = new BorderPane();

        positions.setTop(positionPanel);
        positions.setBottom(positionTable);
        positionPanel.setSpacing(20);
        panel.setAlignment(Pos.TOP_CENTER);
        panelSelection.setAlignment(Pos.TOP_CENTER);
        panelInsert.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(positionPanel, new Insets(10, 0, 0, 0));

        Scene scene = new Scene(positions);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.show();
        positionPanel.requestLayout();
    }

    public static VBox getPositionTable() {
        return positionTable;
    }

    public static HBox getPanelInsert(){
        return panelInsert;
    }


    private static void delete(){
        switch (nameTable){
            case("room_class"):
                ClassRoomModelTable.delete();
                break;
            case("posts"):
                PostsModelTable.delete();
                break;
            case("services"):
                ServicesModelTable.delete();
                break;
            case("hotel_room"):
                HotelRoomModelTable.delete();
                break;
            case("employee"):
                EmployeeModelTable.delete();
                break;
            case("customer"):
                CustomerModelTable.delete();
                break;
        }

    }

    private static void insert(){
        switch (nameTable){
            case("room_class"):
                ClassRoomModelTable.insert();
                break;
            case("posts"):
                PostsModelTable.insert();
                break;
            case("services"):
                ServicesModelTable.insert();
                break;
            case("hotel_room"):
                HotelRoomModelTable.insert();
                break;
            case("employee"):
                EmployeeModelTable.insert();
                break;
            case("customer"):
                CustomerModelTable.insert();
                break;
        }
    }

    private static void update(){
        switch (nameTable){
            case("room_class"):
                ClassRoomModelTable.update();
                break;
            case("posts"):
                PostsModelTable.update();
                break;
            case("services"):
                ServicesModelTable.update();
                break;
            case("hotel_room"):
                HotelRoomModelTable.update();
                break;
            case("employee"):
                EmployeeModelTable.update();
                break;
            case("customer"):
                CustomerModelTable.update();
                break;
        }
    }
}
