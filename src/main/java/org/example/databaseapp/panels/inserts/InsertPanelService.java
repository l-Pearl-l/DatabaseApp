package org.example.databaseapp.panels.inserts;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;

public class InsertPanelService {
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField nameService = GUI.createTextField("Название услуги");
    private static final TextField price = GUI.createTextField("Цена");

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, nameService, price);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("services", Integer.parseInt(id.getText()), nameService.getText(), Double.parseDouble(price.getText()));
    }

}
