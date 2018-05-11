package tableGUI.view.authorization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tableGUI.view.MainApp;


public class MainAuthorization extends Application {
    private Stage authorizationStage;
    private MainApp mainApp;

    @Override
    public void start(Stage stage){
        try {
            this.authorizationStage = stage;
            authorizationStage.setTitle("Authorization");
            FXMLLoader loader = new FXMLLoader();
            //System.out.println();
            loader.setLocation(MainApp.class.getResource("authorization/AuthorizationView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            authorizationStage.setScene(scene);
            authorizationStage.resizableProperty().setValue(false);
            authorizationStage.show();
            AuthorizationViewController controller = loader.getController();
            controller.setMainAuthorizaion(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public MainApp getMainApp(){
        return mainApp;
    }

    public static void main(String[] args){
        launch(args);
    }
}
