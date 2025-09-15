package baymax.ui;

import java.io.IOException;

import baymax.Baymax;
import baymax.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Baymax using FXML.
 */
public class Main extends Application {
    private final String FILE_PATH = "./data/Baymax.txt";
    private Baymax baymax = new Baymax(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("Baymax");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(icon);

            stage.setMinHeight(220);
            stage.setMinWidth(500);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBaymax(baymax);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
