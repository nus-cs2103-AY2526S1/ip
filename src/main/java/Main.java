import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import misc.PepeException;

/**
 * Main class that wraps around Pepe.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "./data/pepe.txt";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/happy-pepe.jpg"));
    private Image pepeImage = new Image(this.getClass().getResourceAsStream("/images/swaggy-pepe.jpg"));
    private Pepe pepe;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            pepe = new Pepe(DEFAULT_FILE_PATH);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPepe(pepe); // inject the Pepe instance
            stage.show();
        } catch (IOException | PepeException e) {
            e.printStackTrace();
        }
    }
}
