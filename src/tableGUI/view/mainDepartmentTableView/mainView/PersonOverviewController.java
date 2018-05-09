package tableGUI.view.mainDepartmentTableView.mainView;
import dataTable.departments.Department;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import manager.Manager;
import tableGUI.view.mainDepartmentTableView.MainDepartmentTable;
import tableGUI.view.mainDepartmentTableView.departmentTableView.TableOverviewController;
import tableGUI.view.mainDepartmentTableView.mainView.departmentMenuBar.DepartmentsMenuBarOverviewController;

import java.net.URL;

public class PersonOverviewController {
    @FXML
    private TabPane tabTable;

    @FXML
    private ListView<Department> departmentsListView;

    @FXML
    private BorderPane departmentsBorderPane;

    // Ссылка на главное приложение.
    private MainDepartmentTable mainDepartmentTable;
    private Department dep;
    private FXMLLoader loader;
    private URL uri;
    private TableOverviewController controller;
    private boolean destr = false;
    private AnchorPane personOverview;
    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public PersonOverviewController() {

    }

    public void setMenuBarOverview(AnchorPane pane, FXMLLoader loader){
        DepartmentsMenuBarOverviewController controller = loader.getController();
        controller.setMainDepartmentTable(this);
        departmentsBorderPane.setBottom(pane);

    }

    private void setTableOverview(Tab tb, Department dep){
        if (destr){
            destr = false;
            //((AnchorPane)tb.getContent()).getChildren().clear();
            return;
        }
        destr = true;
        if (controller != null){
            try{
                ((AnchorPane)tb.getContent()).getChildren().setAll(new ScrollPane());
                ScrollPane tabOverview = (ScrollPane) ((AnchorPane) tb.getContent()).getChildren().get(0);
                System.out.println(tb.getText());
                tabOverview.setContent(personOverview);
                controller.setCurrentTab(tb);
                controller.setDepartment(dep);
            }catch (Exception e){

            }
        }else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(uri);
                personOverview = (AnchorPane) loader.load();
                //AnchorPane tabOverview = (AnchorPane) tb.getContent();
                ((AnchorPane)tb.getContent()).getChildren().setAll(new ScrollPane());
                ScrollPane tabOverview = (ScrollPane) ((AnchorPane) tb.getContent()).getChildren().get(0);
                System.out.println(tb.getText());
                tabOverview.setContent(personOverview);
                //tb.getContent().
                controller = loader.getController();
                controller.setCurrentTab(tb);
                controller.setDepartment(dep);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void setTable(){
        if (dep != null) {
            setTableOverview(tabTable.getSelectionModel().getSelectedItem(), dep);
        }
    }

    public void selectDepartment(){
        dep = departmentsListView.getSelectionModel().getSelectedItem();
        destr = false;
        setTable();
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        uri = MainDepartmentTable.class.getResource("departmentTableView/TableOverview.fxml");
        tabTable.getSelectionModel().selectFirst();
        ObservableList<Department> a = FXCollections.observableArrayList(Manager.getInstance().getDepartments());
        departmentsListView.setItems(a);
        departmentsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                selectDepartment();
            }
        });
    }


    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainDepartmentTable
     */
    public void setMainDepartmentTable(MainDepartmentTable mainDepartmentTable) {
        this.mainDepartmentTable = mainDepartmentTable;
    }

    public MainDepartmentTable getMainDepartmentTable(){
        return  mainDepartmentTable;
    }
}