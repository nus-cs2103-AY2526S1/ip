import java.io.IOException;

import betty.Betty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for launching the Betty JavaFX application.
 * Handles initialization and primary stage setup.
 */
public class Main extends Application {

    private final Betty betty = new Betty();

    /**
     * Starts the JavaFX application by setting up the primary stage
     * with the main window defined in the FXML file.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Betty");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // Inject the Betty instance into the controller
            fxmlLoader.<MainWindow>getController().setBetty(betty);
            stage.show();
        } catch (IOException e) {
            // Show a user-friendly error dialog
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                                                    javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load the main window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

