package tableGUI.view.employeesEditView;

import dataTable.departments.DepartmentsTable;
import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tableGUI.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class EditEmployeeController {
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
    private TextField fieldPassword;

    private EmployeesEditController employeesEditController;
    private Employee emp;
    public void setEmployeesEditController(EmployeesEditController employeesEditController){
        this.employeesEditController = employeesEditController;
    }

    public void setEmployee(Employee emp){
        this.emp = emp;
        this.fieldPassword.setText(emp.getPassword());
        this.fieldAdress.setText(emp.getAddress());
        this.fieldPosition.setText(emp.getPosition());
        this.fieldBirthday.setText(emp.getBrthd());
        this.fieldAge.setText(emp.getBrthd());
        this.fieldSurname.setText(emp.getScnName());
        this.fieldName.setText(emp.getFstName());
    }

    public void editEmployee(){
        String name, surname, birthday, position, address, password;
        Integer age;
        try{
            age = Integer.parseInt(fieldAge.getText());
        }catch (Exception e){
            Util.viewAlertWindow("Use numbers in field Age");
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
            Employee newEmp = new Employee(password, emp.getID(), name, surname, birthday, age, position, address, emp.getAccess());
            ArrayList<String> departmentsName = new ArrayList();
            departmentsName.addAll(EmployeeTable.getInstance().getEmployeesDepartments(emp));
            departmentsName.forEach(depName -> {
                try{
                    DepartmentsTable.getInstance().getDepartment(depName).deleteEmployee(emp);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            EmployeeTable.getInstance().deleteEmployee(emp);
            EmployeeTable.getInstance().addEmployee(newEmp);
            departmentsName.forEach(depName -> {
                try{
                    DepartmentsTable.getInstance().getDepartment(depName).addEmployee(newEmp);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            employeesEditController.closeEditEmployeeWindow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
