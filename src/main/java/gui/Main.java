package gui;

import java.io.IOException;

import app.Jack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A gui for Duke using FXML.
 */
public class Main extends Application {

    private Jack jack = new Jack("data/Jack.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Jack - Task Manager");
            stage.setScene(scene);
            // sensible minimums to keep layout usable when resized smaller
            stage.setMinWidth(360);
            stage.setMinHeight(480);
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setJack(jack);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
