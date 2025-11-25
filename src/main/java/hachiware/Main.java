package hachiware;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private HachiwareWrapper hachiware = new HachiwareWrapper("./data/Hachiware.Hachiware.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Hachiware");
            fxmlLoader.<MainWindow>getController().setHachiware(hachiware);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
