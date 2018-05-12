package tableGUI.view.mainDepartmentTableView.mainView.departmentMenuBar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.Manager;
import tableGUI.view.MainApp;
import tableGUI.view.departmentsEditView.DepartmentsEditController;
import tableGUI.view.employeesEditView.EmployeesEditController;
import tableGUI.view.mainDepartmentTableView.MainDepartmentTable;
import tableGUI.view.mainDepartmentTableView.mainView.PersonOverviewController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class DepartmentsMenuBarOverviewController {
    @FXML
    private Button buttonDepartments;

    @FXML
    private Button buttonEmployees;

    private PersonOverviewController personOverviewController;

    @FXML
    public void initialize(){
        //if (Manager.getInstance().getAccess() == 0) return;
        if (Manager.getInstance().getAccess() == 1){
            buttonEmployees.visibleProperty().setValue(false);
        }
        if (Manager.getInstance().getAccess() == 2){
            buttonDepartments.visibleProperty().setValue(false);
        }
        if (Manager.getInstance().getAccess() == 3 || Manager.getInstance().getAccess() == 4){
            buttonEmployees.visibleProperty().setValue(false);
            buttonDepartments.visibleProperty().setValue(false);
        }
    }

    public void setMainDepartmentTable(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

    public void editDepartments(){
        Stage editDepartmentWindow = new Stage();
        editDepartmentWindow.setTitle("Edit departments");
        editDepartmentWindow.initOwner(personOverviewController.getMainDepartmentTable().getPrimaryStage());
        editDepartmentWindow.initModality(Modality.WINDOW_MODAL);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("departmentsEditView/DepartmentsEditOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            DepartmentsEditController controller = loader.getController();
            controller.setPersonOverviewController(personOverviewController);
            controller.setStage(editDepartmentWindow);
            Scene scene = new Scene(anchorPane);
            editDepartmentWindow.setScene(scene);
            editDepartmentWindow.setOnHidden(e -> {
                controller.shutdown();
                //Platform.exit();
            });
            editDepartmentWindow.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editEmployees(){
        Stage editEmployeeWindow = new Stage();
        editEmployeeWindow.setTitle("Edit employees");
        editEmployeeWindow.initOwner(personOverviewController.getMainDepartmentTable().getPrimaryStage());
        editEmployeeWindow.initModality(Modality.WINDOW_MODAL);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("employeesEditView/EmployeesEditOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            EmployeesEditController controller = loader.getController();
            controller.setPersonOverviewController(personOverviewController);
            controller.setStage(editEmployeeWindow);
            Scene scene = new Scene(anchorPane);
            editEmployeeWindow.setScene(scene);
            editEmployeeWindow.setOnHidden(e -> {
                controller.shutdown();
            });
            editEmployeeWindow.show();
        }catch (Exception e){

        }
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

}
