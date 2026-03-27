package marvin.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marvin.Marvin;

/**
 * Entrypoint to the Marvin gui.
 */
public class Main extends Application {

    private final Marvin marvin = new Marvin();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Marvin");
            fxmlLoader.<MainWindow>getController().setMarvin(marvin);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
