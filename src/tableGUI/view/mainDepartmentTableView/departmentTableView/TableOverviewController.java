package tableGUI.view.mainDepartmentTableView.departmentTableView;

import dataTable.departments.CalendarDep;
import dataTable.departments.Department;
import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.util.Callback;
import javafx.util.Pair;

import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tableGUI.model.DepartmentModel;

public class TableOverviewController {
    private Tab currentTab;
    private Department dep;
    private int monthId;
    private ObservableList<Pair<Employee, CalendarDep>> personData;
    private DepartmentModel departmentModel;
    //public static ArrayList<TableOverviewController> instances = new ArrayList<>();

    @FXML
    private TableView<Pair<Employee, CalendarDep>> personTable;

    @FXML
    private TableColumn<Pair<Employee, CalendarDep>, String> nameID;

    @FXML
    private TableColumn<Pair<Employee, CalendarDep>, String> positionID;

    @FXML
    private TableColumn<Pair<Employee, CalendarDep>, Integer> idID;

    @FXML
    private void initialize(){
        //instances.add(this);
        //int i = 0;
        nameID.setCellValueFactory(cellData -> cellData.getValue().getKey().getFstNameProperty().concat(" ").concat(cellData.getValue().getKey().getScnNameProperty()));
        positionID.setCellValueFactory(cellData -> cellData.getValue().getKey().getPositionProperty());
        idID.setCellValueFactory(cellData -> cellData.getValue().getKey().getIdProperty().asObject());
        for (Integer i = 0; i < 31; ++i){
            i++;
            TableColumn<Pair<Employee, CalendarDep>, String> tableColumn = new TableColumn(i.toString());
            tableColumn.setMaxWidth(30);
            i--;
            final int num = i;
            tableColumn.setCellValueFactory(cellData -> {
                final int j = num;
                return  new SimpleStringProperty(cellData.getValue().getValue().getMonth(monthId).get(j).getValue().toString());
            });
            personTable.getColumns().add(tableColumn);
        }
    }


    public void setCurrentTab(Tab currentTab) {
        this.currentTab = currentTab;
        if (dep == null) return;
        monthId = -1;
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        switch (currentTab.getText()){
            case ("January"):{
                monthId = 0;
                break;
            }
            case("February"):{
                monthId = 1;
                break;
            }
            case("March"):{
                monthId = 2;
                break;
            }
            case ("April"):{
                monthId = 3;
                break;
            }
            case ("May"):{
                monthId = 4;
                break;
            }
            case ("June"):{
                monthId = 5;
                break;
            }
            case ("July"):{
                monthId = 6;
                break;
            }
            case ("August"):{
                monthId = 7;
                break;
            }
            case ("September"):{
                monthId = 8;
                break;
            }
            case ("October"):{
                monthId = 9;
                break;
            }
            case ("November"): {
                monthId = 10;
                break;
            }
            case ("December"):{
                monthId = 11;
                break;
            }
        }
        if (monthDays[monthId] > (personTable.getColumns().size() - 3)){
            int n = personTable.getColumns().size() - 3;
            for (Integer i =  n + 1; i <= monthDays[monthId]; ++i){
                TableColumn<Pair<Employee, CalendarDep>, String> a = new TableColumn(String.valueOf(i) );

                a.setMaxWidth(30);
                personTable.getColumns().add(a);
            }
        }else{
            personTable.getColumns().remove(monthDays[monthId] + 3, (personTable.getColumns().size()));
        }
        for (int i = 3; i < personTable.getColumns().size(); ++i){
            final int num = i;
            ((TableColumn<Pair<Employee, CalendarDep>, String>) personTable.getColumns().get(i)).setCellValueFactory(cellData -> {
                final int j = num;
                return  new SimpleStringProperty(cellData.getValue().getValue().getMonth(monthId).get(j-3).getValue().toString());
            });
        }
    }

    public void setDepartment(Department dep) {
        this.dep = dep;
        try {
            DepartmentModel departmentModel = new DepartmentModel(dep);
            personData = FXCollections.observableArrayList(departmentModel.getTableLine());
            personTable.setItems(personData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
