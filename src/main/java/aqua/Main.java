package aqua;

import java.io.IOException;

import aqua.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Aqua aqua = new Aqua("aqua.txt");

    /**
     * Starts the GUI application.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Aqua");
            fxmlLoader.<MainWindow>getController().setAqua(aqua);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
