package org.example.databaseapp.panels.inserts;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;

public class InsertPanelHotelRoom {
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField idCustomer = GUI.createTextField("id гостя");
    private static final TextField numberOfPeople = GUI.createTextField("Количество людей");
    private static final TextField idClassRoom = GUI.createTextField("id класса номера");
    private static final TextField additionalService = GUI.createTextField("Услуга");
    private static final TextField floor = GUI.createTextField("Этаж");
    private static final TextField price = GUI.createTextField("Цена");
    private static final TextField busy = GUI.createTextField("Занятость");

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, idCustomer, numberOfPeople, idClassRoom, additionalService, floor, price, busy);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("hotel_room", Integer.parseInt(id.getText()),
                Integer.parseInt(numberOfPeople.getText()), Integer.parseInt(idClassRoom.getText()),
                Integer.parseInt(floor.getText()), Double.parseDouble(price.getText()), busy.getText(),
                Integer.parseInt(additionalService.getText()),
                Integer.parseInt(idCustomer.getText()));
    }

}
