package tableGUI.view.authorization;

import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import manager.Manager;
import tableGUI.view.authorization.MainAuthorization;
import tableGUI.view.mainDepartmentTableView.MainDepartmentTable;



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
                viewAlertWindow("Please, use only numeral");
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
                    viewAlertWindow("Wrong password");
                }
            }else{
                viewAlertWindow("There is no employee with those ID(" + id +")");
            }
        }catch (Exception e){
            viewAlertWindow(e.getMessage());
        }
    }

    private void viewAlertWindow(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setMainAuthorizaion(MainAuthorization mainAuthorizaion){
        this.mainAuthorizaion = mainAuthorizaion;
    }
}
