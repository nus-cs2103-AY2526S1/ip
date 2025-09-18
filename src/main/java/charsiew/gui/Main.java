package charsiew.gui;

import java.io.IOException;

import charsiew.CharSiew;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ChatBot using FXML.
 */
public class Main extends Application {

    private CharSiew charSiew = new CharSiew("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(charSiew); // inject the CharSiew instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
