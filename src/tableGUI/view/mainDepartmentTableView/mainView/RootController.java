package tableGUI.view.mainDepartmentTableView.mainView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tableGUI.view.MainApp;
import tableGUI.view.mainDepartmentTableView.MainDepartmentTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class RootController {
    private MainDepartmentTable mainDepartmentTable;

    public void setMainApp(MainDepartmentTable mainDepartmentTable){
        this.mainDepartmentTable = mainDepartmentTable;
    }

    public void openAbout(){
        Stage helpAbout = new Stage();
        helpAbout.setTitle("About");
        helpAbout.setResizable(false);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainDepartmentTable.class.getResource("mainView/HelpAboutOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            helpAbout.setScene(scene);
            helpAbout.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
