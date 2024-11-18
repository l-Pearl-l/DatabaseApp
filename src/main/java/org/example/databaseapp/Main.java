package org.example.databaseapp;

import javafx.application.Application;

import javafx.stage.Stage;
import org.example.databaseapp.windows.WindowMain;


public class Main extends Application {

    @Override
    public void start(Stage stage){
        WindowMain.createWindow(stage);
    }

    public static void main(String[] args){
        launch();
    }
}