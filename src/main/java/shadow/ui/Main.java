package shadow.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the main application class of a JavaFX program.
 * The {@code Main} class extends {@code Application} and serves as the entry point for the JavaFX GUI.
 * It is responsible for initializing and displaying the primary stage of the application.
 * The application is configured to load the main user interface layout from an FXML file.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by initializing and displaying the main stage.
     * This method loads the primary user interface from an FXML file and sets it as the scene of the stage.
     * It also handles any IOExceptions that occur during FXML loading.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
