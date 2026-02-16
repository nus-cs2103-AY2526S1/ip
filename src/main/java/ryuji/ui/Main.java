package ryuji.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI application for Ryuji using FXML for the user interface.
 * <p>The {@code Main} class extends {@link Application} and sets up the main stage for the RyujiCafe
 * application. It loads the main window using FXML, applies the CSS styles, and sets the application icon
 * and other UI configurations.</p>
 */
public class Main extends Application {

    /** Instance of the Ryuji chatbot for interaction */
    private Ryuji ryuji = new Ryuji();

    /**
     * Initializes the primary stage and scene for the application.
     * Loads the FXML layout for the main window, applies the CSS stylesheet,
     * sets the application window icon, and injects the {@code Ryuji} instance into the controller.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML layout file for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create the scene with the loaded layout
            Scene scene = new Scene(ap);

            // Add the CSS stylesheet to the scene
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());

            // Set the title for the window
            stage.setTitle("RyujiCafe");

            // Set the application icon
            Image icon = new Image(this.getClass().getResourceAsStream("/images/RyujiPFP.png"));
            stage.getIcons().add(icon);

            // Set the scene for the stage
            stage.setScene(scene);

            // Set minimum window size
            stage.setMinHeight(720);
            stage.setMinWidth(1280);

            // Inject the Ryuji instance into the MainWindow controller
            fxmlLoader.<MainWindow>getController().setRyuji(ryuji);

            // Show the stage (window)
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
