package org.example.databaseapp.panels;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.example.databaseapp.GUI;
import org.example.databaseapp.modelsTable.*;
import org.example.databaseapp.windows.WindowMain;
import java.lang.reflect.InvocationTargetException;

public class PanelSelected {
    private static final Button buttonHotelRoom = GUI.createButton("Номер");
    private static final Button buttonClassRooms = GUI.createButton("Класс номера");
    private static final Button buttonServices = GUI.createButton("Услуги");
    private static final Button buttonCustomers = GUI.createButton("Гости");
    private static final Button buttonEmployees = GUI.createButton("Сотрудники");
    private static final Button buttonPosts = GUI.createButton("Должности");

    static{
        buttonClassRooms.setOnAction(e -> {
            showModelTable(ClassRoomModelTable.class);
        });

        buttonServices.setOnAction(e -> {
            showModelTable(ServicesModelTable.class);
        });

        buttonPosts.setOnAction(e -> {
           showModelTable(PostsModelTable.class);
        });

        buttonCustomers.setOnAction(e -> {
           showModelTable(CustomerModelTable.class);
        });

        buttonHotelRoom.setOnAction((e -> {
            showModelTable(HotelRoomModelTable.class);
        }));

        buttonEmployees.setOnAction(e -> {
            showModelTable(EmployeeModelTable.class);
        });
    }

    public static HBox createPanel(){
        HBox hbox = new HBox(10, buttonHotelRoom, buttonServices, buttonClassRooms,
                                    buttonCustomers, buttonEmployees, buttonPosts);
        return hbox;
    }

    private static void showModelTable(Class<?> modelTable){
        WindowMain.getPositionTable().getChildren().clear();
        try {
            clearColumns();
            clearPanelInsert();
            WindowMain.getPositionTable().getChildren().add((Node) modelTable.getDeclaredMethod("buildTable").invoke(null));
            WindowMain.getPanelInsert().getChildren().add((Node) modelTable.getDeclaredMethod("createPanel").invoke(null));
            WindowMain.setNameTable((String) modelTable.getDeclaredMethod("getNameTable").invoke(null));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void clearColumns(){
        ClassRoomModelTable.clearColumns();
        EmployeeModelTable.clearColumns();
        CustomerModelTable.clearColumns();
        PostsModelTable.clearColumns();
        ServicesModelTable.clearColumns();
        HotelRoomModelTable.clearColumns();
    }

    private static void clearPanelInsert(){
        WindowMain.getPanelInsert().getChildren().clear();
    }
}
