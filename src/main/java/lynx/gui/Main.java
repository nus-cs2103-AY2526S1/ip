package lynx.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lynx.TaskLynxGui;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TaskLynxGui taskLynx = new TaskLynxGui();

    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setTaskLynx(taskLynx);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mainWindow.farewell();
    }

}