package org.example.databaseapp.panels;

import javafx.scene.layout.HBox;

public abstract class Panel {
    public static void clearInsertPanels(HBox panel){
        panel.getChildren().clear();
    }
}
