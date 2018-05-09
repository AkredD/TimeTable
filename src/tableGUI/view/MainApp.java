package tableGUI.view;

import dataTable.departments.DepartmentsTable;
import dataTable.employees.EmployeeTable;
import javafx.application.Application;
import javafx.stage.Stage;
import manager.Manager;
import tableGUI.view.authorization.MainAuthorization;
import tableGUI.view.mainDepartmentTableView.MainDepartmentTable;

import java.sql.Connection;
import java.sql.DriverManager;


public class MainApp extends Application {
    private static DepartmentsTable instanceDT;
    private static EmployeeTable instanceET;
    private Stage mainStage;

    @Override
    public void start(Stage stage){
        this.mainStage = stage;
        MainAuthorization authorization = new MainAuthorization();
        authorization.setMainApp(this);
        authorization.start(mainStage);
    }

    public void initMainDepartmentTable(){
        MainDepartmentTable departmentTable = new MainDepartmentTable();
        departmentTable.setMainApp(this);
        departmentTable.start(mainStage);
    }

    public static void initDB(){
        Connection c;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:timeTable.db");
            instanceET.setConnection(c);
            instanceET = EmployeeTable.getInstance();

            instanceDT = DepartmentsTable.getInstance();
            instanceDT.setConnection(c);
            instanceDT.initialize();
            Manager.getInstance().getDepartments();

        }catch(Exception e){

        }
    }

    public static void main(String[] args) throws Exception {
        initDB();
        launch(args);
    }

}