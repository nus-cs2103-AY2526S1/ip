package zell;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zell.exception.ZellException;
import zell.storage.Storage;
import zell.task.TaskList;
import zell.ui.Ui;

/**
 * Represents the Zell chatbot.
 */
public class Zell extends Application {
    /** Path to the local storage file */
    private static final String FILE_PATH = "./data/Zell.txt";

    /**
     * Starts the Zell chatbot using JavaFX.
     *
     * @param stage The stage for JavaFx.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Set up stage
            FXMLLoader fxmlLoader = new FXMLLoader(Zell.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setTitle("Zell");
            stage.setScene(scene);

            // Initialize objects that will be used in other classes later
            Storage storage = new Storage(FILE_PATH);
            Parser parser = new Parser();
            TaskList taskList;

            try {
                taskList = new TaskList(storage.loadTasks());
            } catch (ZellException ze) {
                taskList = new TaskList(new ArrayList<>());
            }

            fxmlLoader.<Ui>getController().setFields(taskList, storage, parser);
            stage.show();

            // Display welcome message to user
            fxmlLoader.<Ui>getController().showMessage(ZellMessage.WELCOME_MESSAGE.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
