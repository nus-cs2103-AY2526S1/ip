package momo;

import momo.command.Command;
import momo.exception.MomoException;
import momo.parser.Parser;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Main class for the Momo application.
 * Handles the initialization of components, loading of saved tasks,
 * and the main input-processing loop.
 */
public class Momo {
    /** Handles saving and loading tasks to/from the local file system. */
    private final Storage storage;

    /** Stores the current list of tasks. */
    private final TaskList tasks;

    /** Handles all user interactions and displays messages. */
    private final Ui ui;

    /** Stores the type of the last executed command as a string. */
    private String commandType;

    /**
     * Initializes the Momo application.
     * Loads tasks from storage if available and displays initialization messages.
     */
    public Momo() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        storage.load(tasks);
    }

    /**
     * Determines whether the given user input corresponds to an exit command.
     *
     * @param input the raw user input string.
     * @return {@code true} if the input corresponds to a command that exits the application,
     *         {@code false} otherwise.
     */
    public boolean isExit(String input) {
        try {
            String trimmedInput = ui.readCommand(input);
            Command command = Parser.parseToCommand(trimmedInput);
            return command.isExit();
        } catch (MomoException e) {
            return false;
        }
    }

    /**
     * Returns the welcome message when starting the application.
     *
     * @return the welcome message as a string.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Processes user input and generates a response.
     *
     * <p>This method parses the input into a command, executes it, updates the
     * {@code commandType} field for GUI styling, and returns the response string.
     * If a {@link MomoException} occurs during parsing or execution, it returns
     * the error message and sets {@code commandType} to "Error".</p>
     *
     * @param input The raw user input string.
     * @return A string containing the response to the user input, or an error message if an exception occurs.
     */
    public String getResponse(String input) {
        try {
            String trimmedInput = ui.readCommand(input);
            Command command = Parser.parseToCommand(trimmedInput);
            String response = command.execute(tasks, ui, storage);
            commandType = command.getClass().getSimpleName();
            return response;
        } catch (MomoException e) {
            commandType = "Error";
            return e.getMessage();
        }
    }

    /**
     * Returns the type of the last executed command as a string.
     *
     * <p>This is used by the GUI to determine the appropriate styling for the dialog box.</p>
     *
     * @return The last command type, or "Error" if the last execution failed.
     */
    public String getCommandType() {
        return commandType;
    }
}
