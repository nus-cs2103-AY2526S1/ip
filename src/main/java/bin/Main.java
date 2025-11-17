package bin;

import bin.ui.Bin;
import java.io.IOException;

import bin.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bin using FXML.
 */
public class Main extends Application {

    private Bin bin = new Bin("data/bin.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBin(bin);  // inject the Bin instance
            stage.setTitle("Bin - Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
