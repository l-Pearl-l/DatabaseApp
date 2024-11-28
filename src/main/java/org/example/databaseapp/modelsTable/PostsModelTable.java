package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.databaseapp.Database;
import org.example.databaseapp.GUI;
import org.example.databaseapp.panels.Panel;
import org.example.databaseapp.tables.ClassRoom;
import org.example.databaseapp.tables.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostsModelTable {
    private static final String nameTable = "posts";
    private static final TableView<Post> table = new TableView<>();
    private static final TableColumn<Post, Integer> postCodeColumn = new TableColumn<>("Код должности");
    private static final TableColumn<Post, String> postNameColumn = new TableColumn<>("Название должности");
    private static final TableColumn<Post, Double> salaryColumn = new TableColumn<>("Зарплата");
    private static final ResultSet dataDB = Post.getDataFromDB();
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField namePost = GUI.createTextField("Название должности");
    private static final TextField salary = GUI.createTextField("Зарплата");


    private static void attachColumn() {
        postCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        postNameColumn.setCellValueFactory(new PropertyValueFactory<>("postName"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(postCodeColumn, postNameColumn, salaryColumn);
    }

    private static void setDataInCell() {
        try {
            while (dataDB.next()) {
                table.getItems().add(new Post(dataDB.getInt("post_code"),
                                              dataDB.getString("post_name"),
                                              dataDB.getDouble("salary")));
                                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNameTable(){
        return PostsModelTable.nameTable;
    }

    public static TableView<Post> buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }

    public static void delete(){
        Post selectedId = table.getSelectionModel().getSelectedItem();
        int id = selectedId.getPostCode();
        try {
            Database.makeQueryDelete(nameTable, "post_code", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, namePost, salary);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("posts", Integer.parseInt(id.getText()), namePost.getText(),
                Double.parseDouble(salary.getText()));
    }

    public static void clearColumns(){
        table.getColumns().clear();
    }

}
