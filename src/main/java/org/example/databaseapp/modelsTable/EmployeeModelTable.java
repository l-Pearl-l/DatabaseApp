package org.example.databaseapp.modelsTable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.databaseapp.tables.ClassRoom;
import org.example.databaseapp.tables.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModelTable {
    private static final TableView<Employee> table = new TableView<>();
    private static final TableColumn employeeIdColumn = new TableColumn<>("Id сотрудника");
    private static final TableColumn fioColumn = new TableColumn<>("Фамилия, имя, отчество");
    private static final TableColumn postCodeColumn = new TableColumn<>("Должность");
    private static final TableColumn phoneColumn = new TableColumn<>("Номер телефона");
    private static final TableColumn educationColumn = new TableColumn<>("Образование");
    private static final TableColumn additionalLanguageColumn = new TableColumn<>("Дополнительный язык");
    private static final ResultSet dataDB = Employee.getDataFromDB();

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
    public static TableView<Employee> buildTable(){
        attachColumn();
        setDataInCell();
        return table;
    }

    public static TableView<Employee> getTable(){
        return table;
    }
}
