package floydai;

import java.io.IOException;

import floydai.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the FloydAI application.
 * <p>
 * It sets up the primary stage, loads the main window layout from FXML,
 * and injects the {@link FloydAI} instance into the {@link FloydAI} controller.
 * </p>
 */
public class Main extends Application {

    /**
     * The core FloydAI instance used by the application.
     */
    private final FloydAI floyd = new FloydAI("./data/FLOYDAI.txt");

    /**
     * Starts the JavaFX application by loading the main window layout and
     * initializing the controller with the FloydAI instance.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setFloyd(floyd);
            stage.show();

            this.floyd.setMainWindow(mainWindow);
            this.floyd.showWelcomeMessage();
        } catch (IOException e) {
            // Print stack trace if the FXML file cannot be loaded
            e.printStackTrace();
        }
    }


}
