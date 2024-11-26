package org.example.databaseapp;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GUI {

    public static Button createButton(String label){
        Button button = new Button(label);
        return button;
    }

    public static TextField createTextField(String placeholder){
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        return textField;
    }
}
