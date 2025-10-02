package bobby.gui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import bobby.main.Bobby;

/**
 * Main entry point for the JavaFX GUI application.
 */
public class Main extends Application {
    private Bobby bobby;

    /**
     * Starts the JavaFX application and sets up the main window.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Determine the directory of the running JAR or class files
            String dataFilePath = getDataFilePath();
            bobby = new Bobby(dataFilePath);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setWidth(700);
            stage.setMinWidth(500);
            stage.setTitle("Bobby");
            fxmlLoader.<MainWindow>getController().setBobby(bobby);
            stage.show();
        } catch (IOException e) {
            // Use robust logging instead of printStackTrace
            System.err.println("Failed to load main window: " + e.getMessage());
        }
    }

    /**
     * Returns the absolute path to the data/tasks.txt file, always next to the JAR or class files.
     */
    private String getDataFilePath() {
        try {
            File jarDir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            File dataFile = new File(jarDir, "data/tasks.txt");
            return dataFile.getAbsolutePath();
        } catch (URISyntaxException e) {
            // Fallback: use working directory
            return "data/tasks.txt";
        }
    }
}