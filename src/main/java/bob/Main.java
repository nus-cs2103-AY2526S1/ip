package bob;

import java.io.IOException;

import bob.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/tasklist.txt";

    private final Bob bob;

    /**
     * Constructs Main.
     *
     * @param filePath the file path to load tasks from and save tasks to.
     */
    public Main(String filePath) {
        bob = new Bob(filePath);
    }

    /**
     * Constructs Main with default file path.
     */
    public Main() {
        this(DEFAULT_FILE_PATH);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert !fxmlLoader.getLocation().getFile().equals("") : "FXML file not loaded";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bob");
            stage.setMinHeight(500);
            stage.setMinWidth(700);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBob(bob);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
