package sofi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for SOFI GUI.
 * Extends JavaFX Application to create the GUI interface.
 */
public class MainApp extends Application {
    
    private SOFI sofi = new SOFI("." + java.io.File.separator + "data" + java.io.File.separator + "sofi.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSofi(sofi);
            stage.setTitle("SOFI - Smart Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
