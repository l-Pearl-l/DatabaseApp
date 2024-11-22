package org.example.databaseapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import org.example.databaseapp.modelsTable.ClassRoomModelTable;

public class GUI {

    public static Button createButton(String label){
        Button button = new Button(label);
        return button;
    }
}
