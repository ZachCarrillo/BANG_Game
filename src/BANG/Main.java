package BANG;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class runs the application, starting with the bang.fxml file
 * @author Zach Carrillo
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("bang.fxml"));
            primaryStage.setTitle("BANG!");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
