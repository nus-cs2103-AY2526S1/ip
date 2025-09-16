package tinkerton.gui;

import java.io.IOException;
import tinkerton.core.Tinkerton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Tinkerton using FXML.
 */
public class Main extends Application {
    /** The file path for saving and loading tasks. */
    private static final String FILE_PATH = "data/tasks.txt";

    /** The Tinkerton application instance. */
    private Tinkerton tinkerton = new Tinkerton(FILE_PATH);

    /**
     * Starts the JavaFX application and initializes the main window.
     *
     * @param stage The primary stage for this application.
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
            fxmlLoader.<MainWindow>getController().setTinkerton(tinkerton);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
