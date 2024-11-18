package org.example.databaseapp.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.databaseapp.GUI;
import org.example.databaseapp.modelsTable.ClassRoomModelTable;

public class WindowMain {
    private static final Button print = GUI.createButton("Распечатать");
    private static final Button update = GUI.createButton("Обновить");
    private static final Button insert = GUI.createButton("Вставить");
    private static final Button delete = GUI.createButton("Удалить");

    static{
        print.setOnAction(e -> {
            WindowPrint.createWindow();
        });
    }

    public static void createWindow(Stage stage){
        HBox panel = new HBox(10, print, update, insert, delete);
        BorderPane positions = new BorderPane();
        panel.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(panel, new Insets(10));
        positions.setTop(panel);
        positions.setBottom(ClassRoomModelTable.buildTable());
        Scene scene = new Scene(positions);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.show();
    }

}
