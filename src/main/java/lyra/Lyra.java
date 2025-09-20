package lyra;

import lyra.util.Storage;
import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.command.Command;
import lyra.util.Parser;
import lyra.exception.LyraException;


/**
 * Main class for the Lyra task management application.
 * Lyra is a command-line interface application that allows users to manage
 * tasks including todos, deadlines, and events.
 */
public class Lyra {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Lyra instance with the specified file path for data storage.
     * Initializes the UI, storage, and task list components.
     * If loading from storage fails, creates an empty task list.
     *
     * @param filePath the file path where task data will be stored and loaded from
     */
    public Lyra(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LyraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Displays welcome message, continuously reads and executes user commands
     * until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(); // Add empty line before command
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LyraException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Main entry point for the Lyra application.
     * Creates a new Lyra instance and runs the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Lyra("data/lyra.txt").run();
    }
}


