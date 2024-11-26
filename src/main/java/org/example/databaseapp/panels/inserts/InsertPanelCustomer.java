package org.example.databaseapp.panels.inserts;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;

public class InsertPanelCustomer {
    private static final HBox panelInsert = new HBox();
    private static final TextField idCustomer = GUI.createTextField("id");
    private static final TextField fio = GUI.createTextField("ФИО");
    private static final TextField dataPassport = GUI.createTextField("Данные паспорта");
    private static final TextField dateArrival = GUI.createTextField("Дата прибытия");
    private static final TextField dateDeparture = GUI.createTextField("Дата выезда");

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(idCustomer, fio, dataPassport, dateArrival, dateDeparture);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("customer", Integer.parseInt(idCustomer.getText()),
                fio.getText(), dataPassport.getText(), dateArrival.getText(), dateDeparture.getText());
    }
}
