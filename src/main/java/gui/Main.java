package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import kane.Kane;

/**
 * A GUI for Kane using FXML.
 */
public class Main extends Application {

    private Kane kane = new Kane("data/kane.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKane(kane);  // inject the Kane instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
