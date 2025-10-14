package lux;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lux.ui.MainWindow;

/**
 * Launcher for JavaFX Application
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // Apply global application stylesheet
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            stage.setTitle("Lux");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLux(new Lux());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
