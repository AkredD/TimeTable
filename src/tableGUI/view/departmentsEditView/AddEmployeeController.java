package tableGUI.view.departmentsEditView;

import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class AddEmployeeController {
    @FXML
    private TextField textID;

    private DepartmentsEditController departmentsEditController;

    public void setDepartmentsEditController(DepartmentsEditController departmentsEditController){
        this.departmentsEditController = departmentsEditController;
    }

    public void addEmployee(){
        Integer id;
        try{
            id = Integer.parseInt(textID.getText());
            if (EmployeeTable.getInstance().getEmployee(id) != null){
                Employee a = EmployeeTable.getInstance().getEmployee(id);
                if (!departmentsEditController.getSelectedDepartment().getEmployees().contains(a)){
                    departmentsEditController.getSelectedDepartment().addEmployee(a);
                    departmentsEditController.closeAddition();
                }else{
                    viewAlertWindow("Department " + departmentsEditController.getSelectedDepartment().getName() +" already contains this employee");
                }
            }else{
                viewAlertWindow("There is't employee with this ID. ");
            }

        }catch (Exception e){
            viewAlertWindow("ID can contains only numbers");
        }
    }

    private void viewAlertWindow(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cancel(){
        departmentsEditController.closeAddition();
    }
}
