package Mithrandir.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main GUI application class for the Mithrandir task management system.
 * This class initializes and launches the JavaFX application, setting up the main window
 * and connecting it with the Mithrandir application logic.
 */
public class GUi extends Application {

    /** The main application instance that handles the business logic. */
    private Mithrandir.Application mithrandir;

    /**
     * Constructs a new GUi instance and initializes the Mithrandir application.
     * This constructor creates a new instance of the main application logic
     * that will be used by the GUI components.
     */
    public GUi() throws Exception {
        mithrandir = new Mithrandir.Application();
        mithrandir.loadTaskList();
    }

    /**
     * The main entry point for the JavaFX application.
     * This method is called after the application has been initialized and is ready to start.
     * It loads the main FXML layout, sets up the primary stage, and initializes the main window controller.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     * @throws RuntimeException if there is an error loading the FXML file.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML layout for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(GUi.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            
            // Set up the scene and stage
            Image icon = new Image(getClass().getResourceAsStream("/images/OneRing.png"));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            stage.setTitle("Mithrandir");
            stage.getIcons().add(icon);

            
            // Initialize the controller with the application instance
            fxmlLoader.<MainWindow>getController().setMithrandir(mithrandir);
            
            // Display the stage
            stage.show();
            
            // Show the greeting message
            fxmlLoader.<MainWindow>getController().greet();
        } catch (IOException e) {
            // Wrap the checked exception in a runtime exception
            throw new RuntimeException("Failed to load the main window FXML", e);
        }
    }
}