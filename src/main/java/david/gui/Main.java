package david.gui;

import java.io.IOException;

import david.David;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for David using FXML.
 */
public class Main extends Application {

    private David david = new David("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("David");
            stage.show();
            fxmlLoader.<MainWindow>getController().setDavid(david);  // inject the David instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
