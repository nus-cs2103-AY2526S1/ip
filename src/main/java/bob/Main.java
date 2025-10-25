package bob;

import java.io.IOException;

import bob.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Bob bob = new Bob();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            javafx.scene.Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBob(bob);
            stage.setTitle("Bob");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
