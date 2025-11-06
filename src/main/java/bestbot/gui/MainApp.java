package bestbot.gui;

import bestbot.Bestbot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application class for Bestbot GUI.
 *
 * This class is the entry point of the JavaFX application. It initializes
 * the GUI, loads the FXML layout, injects the backend logic, and displays
 * the main window.
 */
public class MainApp extends Application {

    /**
     * Starts the JavaFX application.
     *
     * Steps performed:
     * 1. Load the MainWindow.fxml file using FXMLLoader.
     * 2. Create a Scene from the loaded FXML.
     * 3. Retrieve the controller from the FXML and inject the Bestbot backend.
     * 4. Set the scene on the primary stage, set the title, and show the stage.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML layout for the main window
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Retrieve the controller to inject the backend
        MainWindow controller = fxmlLoader.getController();
        controller.setBestbot(new Bestbot("bestbot.txt"));

        // Configure the primary stage
        stage.setScene(scene);
        stage.setTitle("Bestbot");
        stage.show();
    }

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
