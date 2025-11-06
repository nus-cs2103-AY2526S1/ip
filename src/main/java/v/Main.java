package v;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import v.ui.MainWindow;

/**
 * A GUI for V using FXML.
 */
public class Main extends Application {

    private V v = new V();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("V - Voice for the Voiceless");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // Set V's Guy Fawkes mask as application icon
            try {
                stage.getIcons().add(new javafx.scene.image.Image(
                    Main.class.getResourceAsStream("/images/v-mask.png")));
            } catch (Exception e) {
                System.err.println("Could not load application icon: " + e.getMessage());
            }
            fxmlLoader.<MainWindow>getController().setV(v); // inject the V instance
            stage.show();
            stage.toFront(); // Bring window to front
            stage.requestFocus(); // Request focus
            stage.setAlwaysOnTop(true); // Force window to top
            stage.setAlwaysOnTop(false); // Remove always on top after showing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Only needed if you want to run Main directly (we launch via Launcher)
    public static void main(String[] args) {
        launch(args);
    }
}
