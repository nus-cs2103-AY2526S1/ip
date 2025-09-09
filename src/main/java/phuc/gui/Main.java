package phuc.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import phuc.Phuc;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Phuc phuc = new Phuc();

    /**
     * Initializes and displays the main application window.
     * Loads the FXML layout, sets up the scene, injects dependencies,
     * and shows the primary stage.
     *
     * @param stage the primary stage for this application
     * @throws RuntimeException if FXML loading fails, wrapping the original IOException
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPhuc(phuc);
            stage.setMinHeight(630);
            stage.setMinWidth(417);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

