import java.io.IOException;

import bestie.Bestie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bestie using FXML.
 */
public class Main extends Application {

    private Bestie bestie = new Bestie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBestie(bestie);  // inject the Bestie instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
