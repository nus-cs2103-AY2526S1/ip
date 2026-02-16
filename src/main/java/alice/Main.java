package alice;

import java.io.IOException;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    String filePath = Path.of("data", "alice.txt").toString();
    private Alice alice = new Alice(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Alice");
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setAlice(new Alice("data/alice.txt"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

