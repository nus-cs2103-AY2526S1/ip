package thopz;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Thopz thopz = new Thopz("TaskStorage.txt");

    /**
     * Starts the JavaFX application by loading the main FXML layout,
     * initializing the MainWindow controller with the Thopz instance
     *
     * @param stage the stage for this application where scene will be set
     *
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setThopz(thopz);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
