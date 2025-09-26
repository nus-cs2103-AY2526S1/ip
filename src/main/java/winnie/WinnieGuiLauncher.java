package winnie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.MainWindow;

/**
 * A GUI for Winnie using FXML.
 */
public class WinnieGuiLauncher extends Application {
    private Storage storage;
    private TaskList tasks;
    private static String filePath;

    /**
     * Initializes the application data.
     */
    @Override
    public void init() {
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WinnieGuiLauncher.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Winnie");
            fxmlLoader.<MainWindow>getController().setData(tasks, storage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main entry point for the GUI application.
     */
    public static void main(String[] args) {
        filePath = "./data/winnie.txt";
        Application.launch(WinnieGuiLauncher.class, args);
    }
}
