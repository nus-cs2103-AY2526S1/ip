package alpaca;

import alpaca.command.Command;
import alpaca.task.TaskList;

/**
 * The main entry point for the Alpaca chatbot application.
 * Handles initialization, loading of tasks, and the main command loop.
 */
public class Alpaca {

    /** Handles saving and loading tasks to/from file. */
    private final Storage storage;

    /** List of tasks currently loaded. */
    private TaskList tasks;

    /** User interface handler. */
    private final Ui ui;

    /**
     * Constructs an Alpaca instance with a specific file path for storing tasks.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Alpaca(String filePath) {
        assert filePath != null && !filePath.isBlank() : "File path cannot be null or empty";

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs an Alpaca instance with the default file path "data/tasks.txt".
     */
    public Alpaca() {
        this("data/tasks.txt");
    }

    /**
     * Runs the command loop in the console.
     * Continuously reads user input, executes commands, and exits on command termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    /**
     * Generates a response string for the given user input without blocking.
     * Useful for GUI integration.
     *
     * @param input User input string.
     * @return The response string from Alpaca.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        Command command = Parser.parse(input);
        return command.execute(tasks, ui, storage);
    }

    /**
     * Main entry point for console mode.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Alpaca("data/duke.txt").run();
    }
}
