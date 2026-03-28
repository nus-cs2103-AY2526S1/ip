package tweety.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tweety.Tweety;

/**
 * A GUI for Tweety using FXML.
 */
public class Main extends Application {

    private Tweety tweety = new Tweety();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Tweety");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTweety(tweety);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
