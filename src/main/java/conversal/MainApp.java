package conversal;

import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.GuiUi;
import conversal.ui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX entry point of the {@link Conversal} application.
 *
 * Responsible for initialising the main logic and
 * setting up the primary stage with the main FXML layout.
 *
 */
public class MainApp extends Application {
    private Conversal conversal;

    /**
     * Called by the JavaFX runtime to start the application.
     *
     * Loads the main window from FXML, connects the controller to the
     * {@link Conversal} core instance, and displays the stage.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     * @throws Exception if the FXML cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        GuiUi ui = new GuiUi();
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks = new TaskList(storage.load());
        conversal = new Conversal(ui, storage, tasks);

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
        AnchorPane root = loader.load();

        MainWindowController controller = loader.getController();
        controller.setConversal(conversal);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Conversal");
        stage.setScene(scene);
        stage.show();

        ui.welcomeMessage();
        controller.showConversal(ui.flush());
    }
}
