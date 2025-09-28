package alpaca.gui;

import java.io.IOException;

import alpaca.Alpaca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main JavaFX application class for the Alpaca GUI.
 * <p>
 * Loads the main window FXML and injects an Alpaca instance for handling
 * user commands and interactions.
 */
public class Main extends Application {

    private final Alpaca alpaca = new Alpaca();

    /**
     * Starts the JavaFX application by loading the main window FXML,
     * setting up the scene, and showing the stage.
     *
     * @param stage The primary stage for this application, provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage cannot be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXMLLoader must not be null";

            AnchorPane root = fxmlLoader.load();
            assert root != null : "Root AnchorPane must not be null";

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Alpaca Task Manager"); // Add this line here!

            // Inject the Alpaca instance into the controller
            MainWindow controller = fxmlLoader.getController();
            assert controller != null : "Controller must not be null";
            controller.setAlpaca(alpaca);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}