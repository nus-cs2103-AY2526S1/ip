import java.io.IOException;

import angus.Angus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Angus using FXML.
 */
public class Main extends Application {

    private Angus angus = new Angus();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Angus");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAngus(angus); // inject the Angus instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
