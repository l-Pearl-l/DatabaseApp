package org.example.databaseapp.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.PanelSelected;

import java.lang.reflect.InvocationTargetException;

public class WindowMain {
    private static final Button selectionTable = GUI.createButton("Выбрать таблицу");
    private static final Button update = GUI.createButton("Обновить");
    private static final Button insert = GUI.createButton("Вставить");
    private static final Button delete = GUI.createButton("Удалить");
    private static final HBox panelSelection = new HBox();
    private static final VBox positionTable = new VBox();
    private static TableView<?> activeTable;

    static{
        selectionTable.setOnAction(e -> {
            panelSelection.getChildren().clear();
            panelSelection.getChildren().add(PanelSelected.createPanel());

        });
        delete.setOnAction(e -> {
           delete();
        });
    }

    public static void createWindow(Stage stage){
        HBox panel = new HBox(10, selectionTable, update, insert, delete);
        VBox positionPanel = new VBox();


        positionPanel.getChildren().addAll(panel, panelSelection);
        BorderPane positions = new BorderPane();

        positions.setTop(positionPanel);
        positions.setBottom(positionTable);

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

    public static void setActiveTable(TableView<?> activeTable){
        WindowMain.activeTable = activeTable;
    }


    private static Class<?> delete(){
        Class<?> selectedId = activeTable.getSelectionModel().selectedItemProperty().getClass();
        System.out.println(selectedId);
        return selectedId;
    }
}
