
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import rafayel.Rafayel;
import rafayel.RafayelException;

/**
 * A GUI for Rafayel using FXML.
 */
public class Main extends Application {

    private final Rafayel rafayel = new Rafayel("src/main/java/data/rafayel.txt");

    public Main() throws RafayelException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRafayel(rafayel); // inject the instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}