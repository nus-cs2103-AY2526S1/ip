package pero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Pero using FXML.
 * Launches the GUI application
 */
public class Main extends Application {

    private final Pero pero = new Pero("Pero_storage.txt"); // /CS2103/ip/Pero_storage.txt

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setPero(pero);  // inject the Pero instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
