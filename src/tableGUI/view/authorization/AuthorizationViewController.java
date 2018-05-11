package tableGUI.view.authorization;

import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import manager.Manager;
import tableGUI.util.Util;


public class AuthorizationViewController {

    @FXML
    private TextField idField;

    @FXML
    private TextField passwordField;


    private MainAuthorization mainAuthorizaion;
    @FXML
    private void initialize(){

    }

    public void logInClick() {
        try {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            }catch (Exception e){
                Util.viewAlertWindow("Please, use only numeral");
                return;
            }
            String password = passwordField.getText();
            Employee e = EmployeeTable.getInstance().getEmployee(id);
            if (e != null){
                if (password.equals(e.getPassword())){
                    Manager.getInstance().setAccess(e.getAccess());
                    mainAuthorizaion.stop();
                    mainAuthorizaion.getMainApp().initMainDepartmentTable();
                }else{
                    Util.viewAlertWindow("Wrong password");
                }
            }else{
                Util.viewAlertWindow("There is no employee with those ID(" + id +")");
            }
        }catch (Exception e){
            Util.viewAlertWindow(e.getMessage());
        }
    }

    public void setMainAuthorizaion(MainAuthorization mainAuthorizaion){
        this.mainAuthorizaion = mainAuthorizaion;
    }
}
