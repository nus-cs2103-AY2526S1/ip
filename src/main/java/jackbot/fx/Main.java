package jackbot.fx;

import java.util.List;

import jackbot.JackbotException;
import jackbot.Parser;
import jackbot.Storage;
import jackbot.TaskList;
import jackbot.task.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX entry point that loads {@code MainWindow.fxml} and wires
 * the Jackbot core components into the controller.
 */
public class Main extends Application {

    /** Creates a new {@code Main} application instance. */
    public Main() { }

    /**
     * Initializes the primary stage, loads FXML, and injects
     * {@link jackbot.Storage}, {@link jackbot.TaskList}, and {@link jackbot.Parser}.
     *
     * @param stage primary stage provided by the JavaFX runtime
     * @throws Exception if FXML fails to load
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));

        // Explicit types (avoid Object):
        Parent root = loader.load();
        MainWindow controller = loader.getController();

        // Wire core
        Storage storage = new Storage("./tasks.txt");
        TaskList tasks;
        try {
            List<Task> loaded = storage.load();
            tasks = new TaskList(loaded);
        } catch (JackbotException e) {
            tasks = new TaskList();
        }
        Parser parser = new Parser();
        controller.initCore(storage, tasks, parser);
        stage.setTitle("Jackbot");
        stage.setScene(new Scene(root, 600, 480));
        stage.show();
    }
}
