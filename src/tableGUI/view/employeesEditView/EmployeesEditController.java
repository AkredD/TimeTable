package tableGUI.view.employeesEditView;

import dataTable.departments.Department;
import dataTable.departments.DepartmentsTable;
import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.Manager;
import tableGUI.view.MainApp;
import tableGUI.view.departmentsEditView.DepartmentsEditController;
import tableGUI.view.mainDepartmentTableView.mainView.PersonOverviewController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class EmployeesEditController {
    @FXML
    private TableView<Employee> tableEmployee;

    @FXML
    private TableColumn<Employee, Integer> columnID;

    @FXML
    private TableColumn<Employee, String> columnName;

    @FXML
    private TableColumn<Employee, String> columnSurname;

    @FXML
    private TableColumn<Employee, Integer> columnAge;

    @FXML
    private TableColumn<Employee, String> columnBirthday;

    @FXML
    private TableColumn<Employee, String> columnPosition;

    @FXML
    private TableColumn<Employee, String> columnAdress;

    @FXML
    private TableColumn<Employee, Integer>  columnAccess;

    private PersonOverviewController personOverviewController;
    private Stage employeesEditStage;
    private Stage addEmployeeWindow;
    private Stage editEmployeeWindow;

    @FXML
    public void initialize(){
        columnID.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        columnName.setCellValueFactory(cellData -> cellData.getValue().getFstNameProperty());
        columnSurname.setCellValueFactory(cellData -> cellData.getValue().getScnNameProperty());
        columnAge.setCellValueFactory(cellData -> cellData.getValue().getAgeProperty().asObject());
        columnBirthday.setCellValueFactory(cellData -> cellData.getValue().getBrthdProperty());
        columnPosition.setCellValueFactory(cellData -> cellData.getValue().getPositionProperty());
        columnAdress.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
        columnAccess.setCellValueFactory(cellData -> cellData.getValue().getAccessProperty().asObject());
        ObservableList<Employee> a = FXCollections.observableArrayList(Manager.getInstance().getEmployees());
        tableEmployee.setItems(a);
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

    public void setStage(Stage employeesEditStage){
        this.employeesEditStage = employeesEditStage;
    }

    public void addEmployee(){
        addEmployeeWindow = new Stage();
        addEmployeeWindow.setTitle("Creating employee");
        addEmployeeWindow.initOwner(employeesEditStage);
        addEmployeeWindow.initModality(Modality.WINDOW_MODAL);
        addEmployeeWindow.resizableProperty().setValue(false);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("employeesEditView/CreateEmployeeOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            CreateEmployeeController controller = loader.getController();
            controller.setEmployeesEditController(this);
            Scene scene = new Scene(anchorPane);
            addEmployeeWindow.setScene(scene);
            addEmployeeWindow.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void shutdown(){
        personOverviewController.refreshDepartments();
    }

    public void closeAddEmployeeWindow(){
        addEmployeeWindow.close();
        refreshEmployees();
    }

    public void deleteEmployee(){
        Employee emp = tableEmployee.getSelectionModel().getSelectedItem();
        if (emp != null) {
            try {
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
                refreshEmployees();
            }catch(Exception e){
                 e.printStackTrace();
            }
        }
    }

    public void editEmployee(){
        Employee emp = tableEmployee.getSelectionModel().getSelectedItem();
        if (emp != null) {
            editEmployeeWindow = new Stage();
            editEmployeeWindow.setTitle("Edit employee");
            editEmployeeWindow.initOwner(employeesEditStage);
            editEmployeeWindow.initModality(Modality.WINDOW_MODAL);
            editEmployeeWindow.resizableProperty().setValue(false);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("employeesEditView/EditEmployeeOverview.fxml"));
                AnchorPane anchorPane = (AnchorPane) loader.load();
                EditEmployeeController controller = loader.getController();
                controller.setEmployeesEditController(this);
                controller.setEmployee(emp);
                Scene scene = new Scene(anchorPane);
                editEmployeeWindow.setScene(scene);
                editEmployeeWindow.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeEditEmployeeWindow(){
        editEmployeeWindow.close();
        refreshEmployees();
    }

    public void refreshEmployees(){
        ObservableList<Employee> a = FXCollections.observableArrayList(Manager.getInstance().getEmployees());
        tableEmployee.setItems(a);
    }



}
