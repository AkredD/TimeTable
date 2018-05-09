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
    private Stage addEmployee;

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

    public Department getSelectedDepartment(){
        return selectedDepartment;
    }

    private void setDepartment(Department dep){
        this.selectedDepartment = dep;
        departmentName.setText(dep.getName());
        ObservableList<Employee> a = FXCollections.observableArrayList(dep.getEmployees());
        tableEmployee.setItems(a);
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController){
        this.personOverviewController = personOverviewController;
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

    }

    public void renameDepartment(){

    }

    public void addEmployeeToDepartment(){
        try {
            this.addEmployee = new Stage();
            addEmployee.setTitle("Addition");
            FXMLLoader loader = new FXMLLoader();
            //System.out.println();
            loader.setLocation(MainApp.class.getResource("departmentsEditView/AddEmployeeOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            addEmployee.setScene(scene);
            addEmployee.initOwner(personOverviewController.getMainDepartmentTable().getPrimaryStage());
            addEmployee.initModality(Modality.WINDOW_MODAL);
            addEmployee.show();
            AddEmployeeController controller = loader.getController();
            controller.setDepartmentsEditController(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void closeAddition(){
        ObservableList<Employee> a = FXCollections.observableArrayList(selectedDepartment.getEmployees());
        tableEmployee.setItems(a);
        addEmployee.close();
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
