package lucid;

// Reused from JavaFX tutorial
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class to start JavaFX GUI
 */
public class Main extends Application {
    private Lucid lucid = new Lucid();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Lucid");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setLucid(lucid); // inject the Lucid instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
