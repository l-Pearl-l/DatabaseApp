package org.example.databaseapp.panels.inserts;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;

public class InsertPanelEmployee {
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField fio = GUI.createTextField("ФИО");
    private static final TextField postCode = GUI.createTextField("Код должности");
    private static final TextField education = GUI.createTextField("Образование");
    private static final TextField phone = GUI.createTextField("Номер телефона");
    private static final TextField additionalLanguage = GUI.createTextField("Второй язык");

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, fio, postCode, education, phone, additionalLanguage);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("employee", Integer.parseInt(id.getText()),
                fio.getText(), Integer.parseInt(postCode.getText()), phone.getText(), education.getText(), additionalLanguage.getText());
    }

}
