package tableGUI.view.employeesEditView;

import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tableGUI.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class CreateEmployeeController {
    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSurname;

    @FXML
    private TextField fieldAge;

    @FXML
    private TextField fieldBirthday;

    @FXML
    private TextField fieldPosition;

    @FXML
    private TextField fieldAdress;

    @FXML
    private TextField fieldID;

    @FXML
    private TextField fieldPassword;

    private EmployeesEditController employeesEditController;

    public void createEmployee(){
        String name, surname, birthday, position, address, password;
        Integer age, id;
        try{
            age = Integer.parseInt(fieldAge.getText());
            id = Integer.parseInt(fieldID.getText());
        }catch (Exception e){
            Util.viewAlertWindow("Use numbers in fields Age and ID");
            return;
        }
        name = fieldName.getText();
        surname = fieldSurname.getText();
        birthday = fieldBirthday.getText();
        position = fieldPosition.getText();
        address = fieldAdress.getText();
        password = fieldPassword.getText();
        if (name.equals("") || surname.equals("") || birthday.equals("") ||
                position.equals("") || address.equals("") || password.equals("")){
            Util.viewAlertWindow("Fill all fields");
            return;
        }
        try {
            if (!EmployeeTable.getInstance().containsOf(id)) {
                Employee newEmp = new Employee(password, id, name, surname, birthday, age, position, address, 1);
                EmployeeTable.getInstance().addEmployee(newEmp);
                employeesEditController.closeAddEmployeeWindow();
            }else{
                Util.viewAlertWindow("Employee with this id also exist");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setEmployeesEditController(EmployeesEditController employeesEditController){
        this.employeesEditController = employeesEditController;
    }


}
