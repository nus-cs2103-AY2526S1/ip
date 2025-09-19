package aurora;

import java.io.IOException;

import aurora.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the JavaFX application.
 * It initializes the primary stage, loads the FXML layout, and sets up
 * the {@link Aurora} instance for use in the UI controller.
 */
public class Main extends Application {

    private final Aurora aurora = new Aurora();

    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Aurora");
            fxmlLoader.<MainWindow>getController().setAurora(aurora); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
