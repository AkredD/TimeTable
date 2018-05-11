package tableGUI.view.departmentsEditView;

import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tableGUI.util.Util;


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
                    departmentsEditController.closeAdditionEmployee();
                }else{
                    Util.viewAlertWindow("Department " + departmentsEditController.getSelectedDepartment().getName() +" already contains this employee");
                }
            }else{
                Util.viewAlertWindow("There is't employee with this ID. ");
            }

        }catch (Exception e){
            Util.viewAlertWindow("ID can contains only numbers");
        }
    }


    public void cancel(){
        departmentsEditController.closeAdditionEmployee();
    }
}
