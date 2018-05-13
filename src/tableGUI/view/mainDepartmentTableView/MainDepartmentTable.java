package tableGUI.view.mainDepartmentTableView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import dataTable.departments.DepartmentsTable;
import dataTable.employees.EmployeeTable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import manager.Manager;
import tableGUI.view.MainApp;
import tableGUI.view.mainDepartmentTableView.mainView.PersonOverviewController;
import tableGUI.view.mainDepartmentTableView.mainView.RootController;
import tableGUI.view.mainDepartmentTableView.mainView.departmentMenuBar.DepartmentsMenuBarOverviewController;

public class MainDepartmentTable extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private PersonOverviewController controllerPersonOverview;
    private AnchorPane table;
    private MainApp mainApp;

    public MainDepartmentTable() {

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();
        showPersonOverview();

        initMenuBarLayout();
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    /**
     * Инициализирует корневой макет.
     */

    private void initMenuBarLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainDepartmentTable.class.getResource("mainView/departmentMenuBar/DepartmentsMenuBarOverview.fxml"));
            AnchorPane menuLayout = (AnchorPane) loader.load();
            controllerPersonOverview.setMenuBarOverview(menuLayout, loader);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainDepartmentTable.class.getResource("mainView/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootController controller = loader.getController();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showPersonOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainDepartmentTable.class.getResource("mainView/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
            controllerPersonOverview = loader.getController();
            controllerPersonOverview.setMainDepartmentTable(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }



    public static void startMainDepartmentTable() {
        launch();
    }

}