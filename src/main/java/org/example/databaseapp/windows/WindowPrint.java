package org.example.databaseapp.windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.databaseapp.GUI;

public class WindowPrint {
    private static Stage windowPrint = new Stage();

    public static void createWindow(){
        Button buttonSelect = GUI.createButton("Выбрать");
        BorderPane positions = new BorderPane();
        positions.setTop(GUI.getSelectionList());
        positions.setBottom(buttonSelect);
        BorderPane.setAlignment(buttonSelect, Pos.BOTTOM_CENTER);
        Scene scene = new Scene(positions);
        windowPrint.setWidth(200);
        windowPrint.setHeight(300);
        windowPrint.setScene(scene);
        windowPrint.show();
    }
}
