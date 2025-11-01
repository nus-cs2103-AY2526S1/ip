package gui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tasks.TaskList;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        TaskList.restoreListFromSave();
        try {
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setWidth(600);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
