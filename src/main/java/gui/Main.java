package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taskbot.TaskBot;

/**
 * GUI for TaskBot
 * Sets up the main window and starts the JavaFX application
 */

public class Main extends Application {
    private TaskBot tb = new TaskBot("./data/TaskBot.TaskBot.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskBot(tb);
            stage.setTitle("TaskBot!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
