package reboot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import reboot.Reboot;

/**
 * A GUI for Reboot using FXML.
 */
public class Main extends Application {

    private final Reboot reboot = new Reboot("reboot.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Reboot");
            fxmlLoader.<MainWindow>getController().setReboot(reboot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

