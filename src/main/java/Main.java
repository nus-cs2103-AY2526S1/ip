import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import ackermann.Ackermann;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ackermann duke = new Ackermann(Paths.get(".", "tasks.txt"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            String css = Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("Ackermann");
            stage.getIcons().add(
                    new Image(getClass().getResourceAsStream("/images/Ackermann.png"))
            );
            fxmlLoader.<MainWindow>getController().setAckermann(duke);
            // inject the Ackermann instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}