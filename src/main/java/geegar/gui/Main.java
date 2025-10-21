package geegar.gui;

import java.io.IOException;

import geegar.Geegar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Geegar using FXML.
 */
public class Main extends Application {

    private Geegar geegar = new Geegar("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Geegar - You Personal Partner-In-Crime");
            fxmlLoader.<MainWindow>getController().setGeegar(geegar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
