package pepe;

import pepe.command.Command;
import pepe.exception.PepeExceptions;
import pepe.parser.Parser;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Main class for the Pepe application, a command-line task manager.
 * <p>
 * This class initializes the application, loads tasks from storage,
 * handles user input through the Ui, and executes commands using the Parser.
 */
public class Pepe {
    //Fields
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private String commandType;

    /**
     * Constructs a new Pepe application instance with the given storage file path.
     * <p>
     * Loads tasks from the storage file. If loading fails, initializes an empty TaskList.
     *
     * @param filePath the path to the storage file for tasks
     */
    public Pepe(String filePath) {
        assert filePath != null && !filePath.isBlank() : "File path should not be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PepeExceptions e) {
            ui.showUiError(e.getMessage());
            tasks = new TaskList();
        }

    }

    /**
     * Runs the main loop of the Pepe application.
     * <p>
     * The loop repeatedly reads user commands, parses them, executes the
     * corresponding command, and updates the task list until the user exits.
     */
    public void run() {
        assert ui != null : "UI should not be null before running the main loop";
        assert tasks != null : "Tasks should not be null before running the main loop";
        ui.showUiGreetUser();
        boolean isExit = false;
        while (!isExit) {
            isExit = handleUserCommand();
        }
    }
    /**
     * Handles a single cycle of user interaction in the main loop.
     * <p>
     * This method reads a command from the user through the {@link Ui},
     * parses it into a {@link Command} using the {@link Parser},
     * and executes the command with the current {@link TaskList} and {@link Storage}.
     * <p>
     * If the command signals application termination, the method returns {@code true}.
     * Otherwise, it continues execution and returns {@code false}.
     * Any {@link PepeExceptions} thrown during parsing or execution are caught,
     * and the error message is displayed via the {@link Ui}.
     *
     * @return {@code true} if the executed command requests program exit,
     *         {@code false} otherwise
     */
    private boolean handleUserCommand() {
        try {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            return c.isExit();
        } catch (PepeExceptions e) {
            ui.showUiError(e.getMessage());
            return false;
        }
    }

    /**
     * Entry point for the Pepe application.
     * <p>
     * Initializes a Pepe instance with the default task storage path and runs the main loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Pepe("data/tasks.txt").run();
    }
    /**
     * Processes a user input command and returns the response as a string.
     * <p>
     * This method parses the input using {@link Parser#parse(String)}, executes
     * the resulting {@link Command} on the current task list, UI, and storage,
     * and updates the {@code commandType} field with the type of command executed.
     * <p>
     * If an exception occurs during parsing or execution, a user-friendly error
     * message is returned.
     *
     * @param input the user's input command; must not be {@code null}
     * @return the result message of executing the command, or an error message if
     *         execution fails
     * @throws AssertionError if {@code input} is {@code null}
     */
    public String getResponse(String input) {
        assert input != null : "Input string should not be null";
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (PepeExceptions e) {
            commandType = null;
            return "WARNING! \n" + e.getMessage();
        }
    }
    /**
     * Returns the type of the last command executed.
     * <p>
     * The command type is the simple class name of the {@link Command} that was
     * last executed via {@link #getResponse(String)}.
     *
     * @return the simple class name of the last executed command, or {@code null}
     *         if no command has been executed yet
     */
    public String getCommandType() {
        return commandType;
    }

}

