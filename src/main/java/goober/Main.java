package goober;

import java.io.IOException;

import goober.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Goober using FXML.
 */
public class Main extends Application {

    private final Goober goober = new Goober();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(Main.class.getResource("/view/styles.css").toExternalForm());

            stage.setTitle("Goober");
            stage.setScene(scene);
            stage.setMinWidth(420);
            stage.setMinHeight(520);
            stage.setResizable(true);

            fxmlLoader.<MainWindow>getController().setGoober(goober);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
