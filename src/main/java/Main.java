import java.io.IOException;

import baymax.Baymax;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the Baymax application.
 * <p>
 * This class sets up and launches the JavaFX GUI using FXML.
 */
public class Main extends Application {

    /**
     * The main Baymax logic instance
     */
    private Baymax baymax = new Baymax();


    /**
     * Starts the JavaFX application by loading the FXML layout and
     * displaying the primary stage.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBaymax(baymax);  // inject the Baymax instance

            stage.setTitle("Baymax Chatbot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
