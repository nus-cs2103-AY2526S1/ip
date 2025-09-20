package piper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import piper.ui.MainWindow;

/**
 * A GUI for Piper using FXML.
 */
public class Main extends Application {

    private Piper piper = new Piper();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            controller.setPiper(piper);
            controller.showGreeting(piper.getGreeting());
            stage.setTitle("Piper");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
