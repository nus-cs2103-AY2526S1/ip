package HawkerUncle;

import HawkerUncle.command.Command;
import HawkerUncle.parser.Parser;
import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.io.IOException;

/**
 * Represents the main entry point for HawkerUncle application.
 * Manages the user interface, task list, and storage operations.
 */

public class HawkerUncle {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "./data/HawkerUncle.txt";

    /**
     * Constructor to initialize the HawkerUncle application.
     * Initializes the user interface, storage, and task list.
     * @param filePath The file path to lad the task data from.
     */
    public HawkerUncle(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e){
            tasks = new TaskList();
        }
    }

    /**
     * Starts the HawkerUncle application.
     * @param args
     */
    public static void main(String[] args) {
        new HawkerUncle(FILE_PATH);
    }
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
    public String getWelcome() {
        return ui.getWelcome();
    }
}