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
import org.example.databaseapp.tables.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeModelTable {
    private static final String nameTable = "employee";
    private static final TableView<Employee> table = new TableView<>();
    private static final TableColumn employeeIdColumn = new TableColumn<>("Id сотрудника");
    private static final TableColumn fioColumn = new TableColumn<>("Фамилия, имя, отчество");
    private static final TableColumn postCodeColumn = new TableColumn<>("Должность");
    private static final TableColumn phoneColumn = new TableColumn<>("Номер телефона");
    private static final TableColumn educationColumn = new TableColumn<>("Образование");
    private static final TableColumn additionalLanguageColumn = new TableColumn<>("Дополнительный язык");
    private static final ResultSet dataDB = Employee.getDataFromDB();
    private static final HBox panelInsert = new HBox();
    private static final TextField id = GUI.createTextField("id");
    private static final TextField fio = GUI.createTextField("ФИО");
    private static final TextField postCode = GUI.createTextField("Код должности");
    private static final TextField education = GUI.createTextField("Образование");
    private static final TextField phone = GUI.createTextField("Номер телефона");
    private static final TextField additionalLanguage = GUI.createTextField("Второй язык");
    private static final Map<String, Object> dataUpdate = new HashMap<>();

    private static void attachColumn(){
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        postCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        additionalLanguageColumn.setCellValueFactory(new PropertyValueFactory<>("additionalLanguage"));
        table.getColumns().addAll(educationColumn, fioColumn, employeeIdColumn, postCodeColumn, phoneColumn, additionalLanguageColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private static void setDataInCell(){
        try {
            while(dataDB.next()){
                table.getItems().add(new Employee(dataDB.getInt("employee_id"),
                                                    dataDB.getString("fio"),
                                                    dataDB.getInt("post_code"),
                                                    dataDB.getString("phone_number"),
                                                    dataDB.getString("education"),
                                                    dataDB.getString("additional_language")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillDataUpdate(){
        dataUpdate.put("fio", fio.getText());
        dataUpdate.put("post_code", Integer.parseInt(postCode.getText()));
        dataUpdate.put("phone_number", phone.getText());
        dataUpdate.put("education", education.getText());
        dataUpdate.put("additional_language", additionalLanguage.getText());
    }

    public static TableView<Employee> buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }

    public static TableView<Employee> getTable(){
        return table;
    }

    public static String getNameTable(){
        return EmployeeModelTable.nameTable;
    }

    public static void delete(){
        Employee selectedId = table.getSelectionModel().getSelectedItem();
        int id = selectedId.getEmployeeId();
        try {
            Database.makeQueryDelete(nameTable, "employee_id", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createPanel(){
        Panel.clearInsertPanels(panelInsert);
        panelInsert.getChildren().addAll(id, fio, postCode, education, phone, additionalLanguage);
        return panelInsert;
    }

    public static void insert(){
        Database.makeQueryInsert("employee", Integer.parseInt(id.getText()),
                fio.getText(), Integer.parseInt(postCode.getText()), phone.getText(), education.getText(), additionalLanguage.getText());
    }

    public static void update(){
        fillDataUpdate();
        Database.makeQueryUpdate(nameTable, dataUpdate, "employee_id", Integer.parseInt(id.getText()));
    }

    public static void clearColumns(){
        table.getColumns().clear();
    }
}
