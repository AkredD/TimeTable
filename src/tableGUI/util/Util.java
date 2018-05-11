package tableGUI.util;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Util {
    public static void viewAlertWindow(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkDepartmentName(String name){
        for (int i = 0; i < name.length(); ++i){
            if (!((name.charAt(i) >= 'a') && (name.charAt(i) <= 'z') ||
                    (name.charAt(i) >= 'A') && (name.charAt(i) <= 'Z') ||
                    (name.charAt(i) >= '0') && (name.charAt(i) <= '9'))) return false;
        }
        return true;
    }
}
