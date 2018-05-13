package tableGUI.view.mainDepartmentTableView.mainView;

import dataTable.departments.ElevationTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;


public class HelpAboutController {
    @FXML
    private TextArea areaAccess;

    @FXML
    private TextArea areaElevation;

    @FXML
    public void initialize(){
        HashMap<String, String> elevations;
        StringBuilder elev = new StringBuilder();
        try {
            elevations = ElevationTable.getInstance().getElavations();
            elevations.forEach((e, t) -> elev.append(e).append("   -   ").append(t).append("\n\n"));
            areaElevation.setText(elev.toString());
        }catch (Exception e){

        }
        StringBuilder acc = new StringBuilder();
        areaAccess.setText(acc.append("0 - Administrator account that has all functions").append("\n\n")
                                .append("1 - Administrator of the department directory - content up to date " +
                                        "directory of departments").append("\n\n")
                                .append("2 - Administrator of the worker's manual - maintaining up-to-date data" +
                                        " on the employees of the enterprise").append("\n\n")
                                .append("3 - Timekeeper - a daily mark about the working time of the employee").append("\n\n")
                                .append("4 - User - only table view").toString());
    }
}
