package tableGUI.view.mainDepartmentTableView.mainView.departmentMenuBar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tableGUI.view.MainApp;
import tableGUI.view.departmentsEditView.DepartmentsEditController;
import tableGUI.view.mainDepartmentTableView.MainDepartmentTable;
import tableGUI.view.mainDepartmentTableView.mainView.PersonOverviewController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class DepartmentsMenuBarOverviewController {
    private PersonOverviewController personOverviewController;

    public void setMainDepartmentTable(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

    public void editDepartments(){
        Stage editWindow = new Stage();
        editWindow.setTitle("Edit departments");
        editWindow.initOwner(personOverviewController.getMainDepartmentTable().getPrimaryStage());
        editWindow.initModality(Modality.WINDOW_MODAL);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("departmentsEditView/DepartmentsEditOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            DepartmentsEditController controller = loader.getController();
            controller.setPersonOverviewController(personOverviewController);
            Scene scene = new Scene(anchorPane);
            editWindow.setScene(scene);
            editWindow.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

}
