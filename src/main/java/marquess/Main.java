package marquess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marquess.ui.MainWindow;

import java.io.IOException;

/**
 * Main class for the GUI
 */
public class Main extends Application {

    private Marquess marquess = new Marquess("./data/marquess.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMarquess(marquess);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
