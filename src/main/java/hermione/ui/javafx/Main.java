package hermione.ui.javafx;

import java.io.IOException;

import hermione.Hermione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main UI class to initialize the JavaFX GUI application.
 */
public class Main extends Application {

    private final Hermione hermione = new Hermione("data/tasks.csv");

    @Override
    public void start(Stage stage) {
        try {
            // Load FXML file and set up the scene
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the Hermione instance into the controller
            fxmlLoader.<MainWindow>getController().setHermione(hermione);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
