package jamal.core;

import java.io.IOException;

import jamal.ui.Jamal;
import jamal.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Controller for the main GUI
 */
public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "data/jamal.ui.Jamal.txt";

    private Jamal jamal = new Jamal(DEFAULT_FILE_PATH, true);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJamal(jamal); //inject the Jamal instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
