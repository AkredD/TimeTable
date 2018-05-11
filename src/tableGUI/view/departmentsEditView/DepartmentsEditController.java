package tableGUI.view.departmentsEditView;


import dataTable.departments.Department;
import dataTable.departments.DepartmentsTable;
import dataTable.employees.Employee;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.Manager;
import tableGUI.util.Util;
import tableGUI.view.MainApp;
import tableGUI.view.mainDepartmentTableView.mainView.PersonOverviewController;

public class DepartmentsEditController {
    @FXML
    private ListView<Department> departmentList;

    @FXML
    private TextField departmentName;

    @FXML
    private TableView<Employee> tableEmployee;

    @FXML
    private TableColumn<Employee, Integer> columnID;

    @FXML
    private TableColumn<Employee, String> columnName;

    @FXML
    private TableColumn<Employee, String> columnSurname;

    @FXML
    private TableColumn<Employee, String> columnPosition;


    private Department selectedDepartment;
    private PersonOverviewController personOverviewController;
    private Stage departmentsEditStage;
    private Stage addEmployee;
    private Stage addDepartment;

    @FXML
    private void initialize() {
        departmentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                setDepartment(newValue);
            }
        });
        ObservableList<Department> a = FXCollections.observableArrayList(Manager.getInstance().getDepartments());
        departmentList.setItems(a);
        columnID.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        columnName.setCellValueFactory(cellData -> cellData.getValue().getFstNameProperty());
        columnSurname.setCellValueFactory(cellData -> cellData.getValue().getScnNameProperty());
        columnPosition.setCellValueFactory(cellData -> cellData.getValue().getPositionProperty());


    }

    public void setStage(Stage departmentsEditStage){
        this.departmentsEditStage = departmentsEditStage;
    }

    public Department getSelectedDepartment(){
        return selectedDepartment;
    }

    private void setDepartment(Department dep){
        if (dep != null) {
            this.selectedDepartment = dep;
            departmentName.setText(dep.getName());
            ObservableList<Employee> a = FXCollections.observableArrayList(dep.getEmployees());
            tableEmployee.setItems(a);
        }
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

    public void shutdown(){
        personOverviewController.refreshDepartments();
    }


    public void deleteDepartment(){
        if (departmentList.getSelectionModel().getSelectedItem() != null){
            try {
                DepartmentsTable.getInstance().deleteDepartment(departmentList.getSelectionModel().getSelectedItem());
                ObservableList<Department> a = FXCollections.observableArrayList(Manager.getInstance().getDepartments());
                departmentList.setItems(a);
            }catch (Exception e){

            }
        }
    }

    public void addDepartment(){
        try {
            this.addDepartment = new Stage();
            addDepartment.setTitle("Addition department");
            FXMLLoader loader = new FXMLLoader();
            //System.out.println();
            loader.setLocation(MainApp.class.getResource("departmentsEditView/AddDepartmentOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            addDepartment.setScene(scene);
            addDepartment.initOwner(departmentsEditStage);
            addDepartment.initModality(Modality.WINDOW_MODAL);
            addDepartment.resizableProperty().setValue(false);
            addDepartment.show();
            AddDepartmentController controller = loader.getController();
            controller.setDepartmentsEditController(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void renameDepartment(){
        String name = departmentName.getText();
        if (!Util.checkDepartmentName(name)){
            Util.viewAlertWindow("Use only english characters or numbers");
            return;
        }
        try {
            if (!name.equals("")) {
                if (!DepartmentsTable.getInstance().getDepartments().containsKey(name)) {
                    DepartmentsTable.getInstance().renameDepartment(selectedDepartment, name);
                    selectedDepartment = DepartmentsTable.getInstance().getDepartment(name);
                    ObservableList<Department> a = FXCollections.observableArrayList(Manager.getInstance().getDepartments());
                    departmentList.setItems(a);
                } else {
                    Util.viewAlertWindow("This department already exist");
                }
            } else {
                Util.viewAlertWindow("Please, enter new name");
            }
        }catch (Exception e){

        }
    }

    public void addEmployeeToDepartment(){
        if (selectedDepartment == null) return;
        try {
            this.addEmployee = new Stage();
            addEmployee.setTitle("Addition employee");
            FXMLLoader loader = new FXMLLoader();
            //System.out.println();
            loader.setLocation(MainApp.class.getResource("departmentsEditView/AddEmployeeOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            addEmployee.setScene(scene);
            addEmployee.initOwner(departmentsEditStage);
            addEmployee.initModality(Modality.WINDOW_MODAL);
            addEmployee.resizableProperty().setValue(false);
            addEmployee.show();
            AddEmployeeController controller = loader.getController();
            controller.setDepartmentsEditController(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void closeAdditionEmployee(){
        ObservableList<Employee> a = FXCollections.observableArrayList(selectedDepartment.getEmployees());
        tableEmployee.setItems(a);
        addEmployee.close();
    }

    public void closeAdditionDepartment(){
        ObservableList<Department> a = FXCollections.observableArrayList(Manager.getInstance().getDepartments());
        departmentList.setItems(a);
        addDepartment.close();
    }

    public void deleteEmployeeFromDepartment(){
        Employee emp = tableEmployee.selectionModelProperty().getValue().getSelectedItem();
        try {
            selectedDepartment.getDepartmentTable().deleteEmployeeFromDepartment(emp);
            ObservableList<Employee> a = FXCollections.observableArrayList(selectedDepartment.getEmployees());
            tableEmployee.setItems(a);
        }catch (Exception e){

        }
    }


}
