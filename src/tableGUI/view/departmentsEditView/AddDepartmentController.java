package tableGUI.view.departmentsEditView;

import dataTable.departments.Department;
import dataTable.departments.DepartmentsTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tableGUI.util.Util;


public class AddDepartmentController {
    @FXML
    private TextField textName;

    private DepartmentsEditController departmentsEditController;

    public void setDepartmentsEditController(DepartmentsEditController departmentsEditController){
        this.departmentsEditController = departmentsEditController;
    }

    public void addDepartment(){
        String name = textName.getText();
        if (!Util.checkDepartmentName(name)){
            Util.viewAlertWindow("Use only english characters or numbers");
            return;
        }
        if (!name.equals("")){
            try {
                if (!DepartmentsTable.getInstance().getDepartments().containsKey(name)) {
                    Department newDep = new Department(name);
                    cancel();
                }else{
                    Util.viewAlertWindow("This department also exist");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Util.viewAlertWindow("Please, enter name");
        }
    }

    public void cancel(){
        departmentsEditController.closeAdditionDepartment();
    }
}
