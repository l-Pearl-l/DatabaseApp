module org.example.databaseapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.databaseapp to javafx.fxml;
    exports org.example.databaseapp;
    exports org.example.databaseapp.windows;
    exports org.example.databaseapp.tables;
    opens org.example.databaseapp.windows to javafx.fxml;
    opens org.example.databaseapp.tables to javafx.fxml;
    exports org.example.databaseapp.modelsTable;
    opens org.example.databaseapp.modelsTable to javafx.fxml;
    exports org.example.databaseapp.panels;
    opens org.example.databaseapp.panels to javafx.fxml;
}