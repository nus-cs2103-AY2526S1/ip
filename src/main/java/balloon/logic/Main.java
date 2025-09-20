package balloon.logic;

import java.io.IOException;

import balloon.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "./data/balloon.txt";
    private Balloon balloon = new Balloon(DEFAULT_FILE_PATH);


    // JavaFX requires Main to have a nullary constructor
    public Main() {

    }

    /**
     * This method is automatically called upon launch of the application
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Balloon");
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setBalloon(balloon); // inject the Balloon instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
