
import ember.ui.EmberUi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Main using FXML.
 */
public class Main extends Application {

    private EmberUi ember = new EmberUi();

    /**
     * Initializes and displays the primary stage.
     * Loads MainWindow.fxml, sets up the Scene and minimum window size,
     * injects the EmberUi instance into the controller, and shows the Stage.
     *
     * Notes:
     * - This runs on the JavaFX Application Thread.
     * - Any IOException during FXML loading is printed to the error stream.
     *
     * @param stage the primary Stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setEmber(ember);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
