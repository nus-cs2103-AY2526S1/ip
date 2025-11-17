import pingu.command.Command;

import pingu.Parser;
import pingu.PinguException;
import pingu.Storage;
import pingu.TaskList;
import pingu.Ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Pingu extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private static final String DEFAULT_FILE_PATH = "data/pingu.txt";

    /**
     * Constructor for the Pingu application.
     * Initializes UI, Storage, and loads tasks from the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Pingu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PinguException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Pingu() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Runs the main loop of the application.
     * Greets the user, reads commands, executes them, and says goodbye on exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PinguException e) {
                ui.showError(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.showLine();
                }
            }
        }
        ui.close();
    }


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Pingu.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPingu(this);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        String message = "";
        boolean isExit = false;
        try {
            Command c = Parser.parse(input);
            message = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (PinguException e) {
            ui.showError(e.getMessage());
            return e.getMessage();
        } finally {
            if (isExit) {
                return "";
            }
        }
        return message;
    }
    
    public static void main(String[] args) {
        new Pingu("data/pingu.txt").run();
    }
}
