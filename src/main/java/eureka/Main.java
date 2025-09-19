package eureka;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The entry point for the Eureka GUI application using JavaFX and FXML.
 * <p>
 * This class sets up the primary stage, loads the FXML layout,
 * and connects the UI with the {@link Eureka} backend.
 */
public class Main extends Application {

    private Eureka eureka = new Eureka("data/record.txt");

    /**
     * Starts the JavaFX application.
     * <p>
     * Loads the {@code MainWindow.fxml} layout, sets up the scene,
     * and injects the {@link Eureka} instance into the controller.
     *
     * @param stage the primary stage provided by the JavaFX runtime
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
            fxmlLoader.<MainWindow>getController().setEureka(eureka); // inject the Eureka instance
            stage.setTitle("Eureka");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
