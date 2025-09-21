package stella;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Creates a GUI for Stella using FXML.
 */
public class Main extends Application {
    private Stella stella = new Stella();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Stella");
            fxmlLoader.<MainWindow>getController().setStella(stella); // inject the Stella instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
