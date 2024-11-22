package org.example.databaseapp.panels;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.databaseapp.GUI;

public class PanelDelete {
    private static final TextField fieldDelete = new TextField();


    public static HBox createPanel(){
        Button buttonDelete = GUI.createButton("Удалить");
        fieldDelete.setPromptText("id");
        HBox panelDelete = new HBox(fieldDelete, buttonDelete);

        return panelDelete;
    }
}
