package choicebot.gui;

import java.io.IOException;

import choicebot.ChoiceBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main JavaFX application class for ChoiceBot.
 * <p>
 * This class is responsible for launching the GUI and initializing the main window
 * with the ChoiceBot instance. It loads the FXML layout, sets up the scene, and
 * displays the primary stage.
 * </p>
 */
public class Main extends Application {

    /** The ChoiceBot instance used by the GUI. */
    private final ChoiceBot choicebot = new ChoiceBot("data/tasks.txt");

    /**
     * Starts the JavaFX application.
     * <p>
     * This method loads the main FXML layout (MainWindow.fxml), sets the scene,
     * injects the ChoiceBot instance into the controller, sets the stage title,
     * and shows the stage.
     * </p>
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load FXML layout
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create a scene with the loaded layout
            Scene scene = new Scene(ap);

            // Set scene and title for the stage
            stage.setScene(scene);
            stage.setTitle("ChoiceBot");

            // Inject ChoiceBot instance into controller
            fxmlLoader.<MainWindow>getController().setChoiceBot(choicebot);

            // Display the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
