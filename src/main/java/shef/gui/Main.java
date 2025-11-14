package shef.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shef.Shef;

/**
 * A GUI for Shef using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "data/data.csv";
    private static final String WINDOW_TITLE = "Shef";

    private Shef shef;

    @Override
    public void start(Stage stage) {
        shef = new Shef(FILE_PATH);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setShef(shef); // inject the Shef instance

            stage.setTitle(WINDOW_TITLE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
