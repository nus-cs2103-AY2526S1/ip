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
            FXMLLoader fxmlLoader = new FXMLLoader(Zell.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);

            Ui ui = new Ui();
            Storage storage = new Storage(FILE_PATH);
            Parser parser = new Parser();
            TaskList taskList;

            try {
                taskList = new TaskList(storage.loadTasks());
            } catch (ZellException ze) {
                // ui.showMessage(ze.toString());
                taskList = new TaskList(new ArrayList<>());
            }

            fxmlLoader.<Ui>getController().setFields(taskList, storage, parser);
            stage.show();

            fxmlLoader.<Ui>getController().showMessage(ZellMessage.WELCOME_MESSAGE.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
