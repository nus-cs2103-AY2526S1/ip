package finixx.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import finixx.Finixx;

/**
 * The JavaFX application class for Finixx.
 * This class initializes and displays the GUI using FXML.
 * It creates a Finixx instance to manage tasks and injects it into the controller.
 */
public class Main extends Application {

    private Finixx finixx = new Finixx("data/tasks.txt");

    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Finixx");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFinixx(finixx);  // inject the Finixx instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
