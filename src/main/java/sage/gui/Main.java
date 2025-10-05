package sage.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sage.main.Sage;

/**
 * A GUI for Sage using FXML.
 */
public class Main extends Application {

    private static final String FILE_PATH = "data/sage.txt";
    private final Sage sage = new Sage(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gui/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Sage");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSage(sage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
