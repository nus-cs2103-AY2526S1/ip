package fatty.gui;

import java.io.IOException;

import fatty.Fatty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Fatty fatty = new Fatty();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fatty - Your Obese Task Manager");
            fxmlLoader.<MainWindow>getController().setFatty(fatty); // inject the fatty instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
