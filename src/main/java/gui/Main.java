package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sunday.Sunday;

/**
 * A GUI for Sunday using FXML.
 * Similar to the one in the tutorial.
 */
public class Main extends Application {

    private final Sunday sunday = new Sunday();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSunday(sunday);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

