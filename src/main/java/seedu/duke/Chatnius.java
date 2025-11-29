package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Main logic class for the Chatnius application.
 * <p>
 * Handles initialization of storage, task list, and UI components.
 * Provides both a CLI loop (via {@link #run()}) and a method for GUI interaction
 * (via {@link #getResponse(String)}).
 * </p>
 */
public class Chatnius {

    /** Default file path used for saving and loading tasks. */
    private static final String DEFAULT_SAVE_PATH = "data/duke.txt";

    /** Manages saving/loading tasks from persistent storage. */
    private final Storage storage;

    /** In-memory list of tasks. */
    private final TaskList tasks;

    /** Handles user interactions and buffered outputs. */
    private final Ui ui;

    /**
     * Creates a new {@code Chatnius} instance.
     *
     * @param filePath The file path to load and save task data.
     */
    public Chatnius(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            loaded = new TaskList();
        }
        this.tasks = loaded;
        assert this.tasks != null : "TaskList should never be null after initialization";
    }

    /**
     * Processes one cycle of user interaction in the CLI.
     * <p>
     * Reads a command, parses it, executes it, and reports errors if any.
     * </p>
     *
     * @return {@code true} if the command was an exit command; {@code false} otherwise.
     */
    private boolean handleOneCommandCycle() {
        try {
            final String fullCommand = ui.readCommand();
            final Command command = Parser.parse(fullCommand);
            assert command != null : "Parser.parse() should never return null";
            command.execute(tasks, ui, storage);
            return command.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return false; // Keep the main path flat.
        }
    }

    /**
     * Runs the CLI version of Chatnius.
     * <p>
     * Continuously reads and executes user commands until an exit command is given.
     * </p>
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            isExit = handleOneCommandCycle();
        }
        ui.close();
    }

    /**
     * Entry point for the CLI program.
     *
     * @param args Optional argument to specify the storage file path.
     */
    public static void main(String[] args) {
        final String filePath = (args.length > 0) ? args[0] : DEFAULT_SAVE_PATH;
        new Chatnius(filePath).run();
    }

    /**
     * Handles one user command and returns the response string (used by the GUI).
     *
     * @param input The raw user input string.
     * @return The output string produced by executing the command.
     */
    public String getResponse(String input) {
        try {
            final Command command = Parser.parse(input);
            assert command != null : "Parser.parse() should never return null";
            command.execute(tasks, ui, storage);
            return ui.flush();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return ui.flush();
        }
    }
}
