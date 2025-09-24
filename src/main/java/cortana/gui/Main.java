package cortana.gui;

import java.io.IOException;
import java.nio.file.Path;

import cortana.core.Cortana;
import cortana.storage.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Cortana cortana = new Cortana(new FileHandler(Path.of("data/tasks.txt")));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setCortana(cortana); // inject the Cortana instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
