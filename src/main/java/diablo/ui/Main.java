package diablo.ui;

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

    private Diablo diablo = new Diablo("src/main/data/diablo.txt");

    /**
     * Starts the GUI
     * @param stage the stage for use in the GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(
                    getClass()
                            .getResource("/view/diablo.css")
                            .toExternalForm()
            );
            stage.setScene(scene);
            stage.setTitle("Diablo");
            fxmlLoader.<MainWindow>getController().setDiablo(diablo);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
