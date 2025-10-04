package angsoontong.gui;

import java.io.IOException;

import angsoontong.AngSoonTong;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML
 */
public class MainApp extends Application {

    /**
     * init AngSoonTong with filePath
     */
    private AngSoonTong angSoonTong = new AngSoonTong("data/tasks.txt");

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * @param stage Stage representing the main application window.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAngSoonTong(angSoonTong);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
