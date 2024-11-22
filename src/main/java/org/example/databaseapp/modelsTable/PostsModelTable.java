package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.tables.ClassRoom;
import org.example.databaseapp.tables.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostsModelTable {
    private static final TableView<Post> table = new TableView<>();
    private static final TableColumn<Post, Integer> postCodeColumn = new TableColumn<>("Код должности");
    private static final TableColumn<Post, String> postNameColumn = new TableColumn<>("Название должности");
    private static final TableColumn<Post, Double> salaryColumn = new TableColumn<>("Зарплата");
    private static final ResultSet dataDB = Post.getDataFromDB();


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

    public static TableView<Post> buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }


    public static TableView<Post> getTable(){
        return table;
    }

}
