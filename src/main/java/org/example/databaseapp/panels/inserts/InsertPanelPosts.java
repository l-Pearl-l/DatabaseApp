package org.example.databaseapp.panels.inserts;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;

public class InsertPanelPosts {
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField namePost = GUI.createTextField("Название должности");
    private static final TextField salary = GUI.createTextField("Зарплата");

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, namePost, salary);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("posts", Integer.parseInt(id.getText()), namePost.getText(),
                                Double.parseDouble(salary.getText()));
    }

}
