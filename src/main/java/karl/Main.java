package karl;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Karl using FXML.
 */
public class Main extends Application {

    private Karl karl = new Karl("data" + java.io.File.separator + "karl.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Karl");
            Image image = new Image("/images/karl.jpeg");
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setKarl(karl);  // inject the Karl instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
