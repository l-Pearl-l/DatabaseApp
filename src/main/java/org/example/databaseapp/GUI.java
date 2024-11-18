package org.example.databaseapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import org.example.databaseapp.modelsTable.ClassRoomModelTable;

public class GUI {
    private static ObservableList items = FXCollections.observableArrayList("Номер",
                            "Класс номера", "Сотрудники", "Услуги", "Гость", "Должности");
    private static ChoiceBox<String> selectionList = new ChoiceBox<>(items);
    private static TableView<ClassRoomModelTable> table = new TableView<>();

    static{
        selectionList.getSelectionModel().select(0);
    }

    public static Button createButton(String label){
        Button button = new Button(label);
        return button;
    }

    public static ChoiceBox<String> getSelectionList(){
        return selectionList;
    }
}
