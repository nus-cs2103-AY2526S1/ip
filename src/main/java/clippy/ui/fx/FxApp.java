package clippy.ui.fx;

import java.io.IOException;

import clippy.Clippy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main application class for the Clippy JavaFX UI.
 */
public class FxApp extends Application {
    private final Clippy clippy = new Clippy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FxApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Clippy");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setClippy(clippy); // inject the Duke instance
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
