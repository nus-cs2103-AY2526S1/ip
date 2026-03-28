package cattis;

import cattis.command.Command;
import cattis.exception.CattisException;
import cattis.task.TaskList;
import cattis.ui.Ui;

/**
 * The main application class that orchestrates the Cattis task management system.
 * <p>
 * This class serves as the central coordinator for all application components including
 * user interface, command parsing, data storage, and task management. It provides both
 * CLI and programmatic interfaces for task operations.
 *
 * @author Kosolpattanadurong
 */
public class Cattis implements CattisInterface {

    /** Default file path for task data persistence */
    private static final String DEFAULT_FILE_PATH = "data/cattis.txt";

    /** User interface component for input/output operations */
    private final Ui ui;

    /** Command parser for interpreting user input */
    private final Parser parser;

    /** Storage component for data persistence */
    private final Storage storage;

    /** Task list container for managing tasks */
    private final TaskList taskList;

    /**
     * Creates a new Cattis instance using the default file path.
     * <p>
     * This constructor delegates to the parameterized constructor with
     * the default file path for task data storage.
     */
    public Cattis() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Creates a new Cattis instance with the specified data file path.
     * <p>
     * Initializes all core components and attempts to load existing task data.
     * If data loading fails (e.g., file not found, corrupted data), the error
     * is displayed to the user but does not prevent application startup.
     *
     * @param filePath the file path where task data is stored
     * @throws IllegalArgumentException if filePath is null or empty
     */
    public Cattis(String filePath) {
        validateFilePath(filePath);

        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();

        loadExistingTasks();
    }

    /**
     * Application entry point.
     * <p>
     * Creates a Cattis instance and starts the main application loop.
     * Includes assertion to verify proper initialization.
     *
     * @param args command line arguments (currently unused)
     */
    public static void main(String[] args) {
        Cattis cattis = new Cattis(DEFAULT_FILE_PATH);
        assert cattis.getUi() != null : "UI component failed to initialize";
        cattis.run();
    }

    /**
     * Gets the task list managed by this application.
     *
     * @return the current task list, never null
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the user interface component.
     *
     * @return the UI instance, never null
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Executes a single command programmatically.
     * <p>
     * This method is designed for non-CLI applications that need to process
     * individual commands without entering the main application loop.
     *
     * @param input the command string to execute
     * @return true if the command was an exit command, false otherwise
     * @throws IllegalArgumentException if input is null
     */
    public boolean runOneCommand(String input) {
        validateInput(input);

        try {
            Command command = parseInput(input);
            if (command == null) {
                return false; // Invalid command, but not exit
            }

            if (command.isExit()) {
                return true; // Exit command received
            }

            executeCommand(command);
            return false; // Command executed successfully

        } catch (CattisException err) {
            ui.showError(err);
            return false; // Error occurred, but not exit
        }
    }

    /**
     * Starts the main application loop.
     * <p>
     * The application lifecycle consists of:
     * <ol>
     *   <li>Display initial welcome messages</li>
     *   <li>Process user input in a continuous loop</li>
     *   <li>Display exit messages when loop terminates</li>
     * </ol>
     */
    public void run() {
        ui.showInitialMessages();

        while (processInputCycle()) {
            // Continue processing until exit condition
        }

        ui.showExitMessages();
    }

    /**
     * Processes one complete input cycle with comprehensive error handling.
     * <p>
     * This method serves as the exception boundary for user input processing,
     * ensuring that errors don't crash the application loop.
     *
     * @return false if application should exit, true to continue processing
     */
    private boolean processInputCycle() {
        try {
            return handleUserInput();
        } catch (CattisException err) {
            ui.showError(err);
            return true; // Continue after error
        } finally {
            ui.showLine(); // Always show separator line
        }
    }

    /**
     * Handles user input processing without exception handling.
     * <p>
     * Processes input through the complete pipeline: read → parse → execute.
     * Uses early returns to avoid deeply nested conditional structures.
     *
     * @return false if application should exit, true to continue processing
     * @throws CattisException if any step in the processing pipeline fails
     */
    private boolean handleUserInput() throws CattisException {
        String input = ui.readCommand();

        if (isEmptyInput(input)) {
            return false; // Empty input signals exit
        }

        Command command = parseInput(input);
        if (command == null) {
            return true; // Invalid command, continue processing
        }

        return executeCommand(command);
    }

    /**
     * Validates file path parameter.
     *
     * @param filePath the file path to validate
     * @throws IllegalArgumentException if filePath is null or empty
     */
    private void validateFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
    }

    /**
     * Validates input parameter.
     *
     * @param input the input to validate
     * @throws IllegalArgumentException if input is null
     */
    private void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    /**
     * Loads existing tasks from storage during initialization.
     * <p>
     * Errors during loading are handled gracefully by displaying them
     * to the user without preventing application startup.
     */
    private void loadExistingTasks() {
        try {
            this.storage.load(this);
        } catch (CattisException err) {
            ui.showError(err);
        }
    }

    /**
     * Checks if the input string represents an exit condition.
     * <p>
     * Currently, empty strings are treated as exit signals.
     *
     * @param input the input string to check
     * @return true if input should cause application exit
     */
    private boolean isEmptyInput(String input) {
        return "".equals(input);
    }

    /**
     * Parses user input into a command object.
     * <p>
     * Displays appropriate error messages for invalid input.
     *
     * @param input the input string to parse
     * @return parsed Command object, or null if parsing failed
     * @throws CattisException if parsing encounters a critical error
     */
    private Command parseInput(String input) throws CattisException {
        Command command = parser.parse(input);
        if (command == null) {
            ui.showMessage("Cannot parse input: " + input);
        }
        return command;
    }

    /**
     * Executes a command and persists any resulting changes.
     * <p>
     * The execution pipeline: execute command → save task list → check exit condition.
     *
     * @param command the command to execute
     * @return false if command indicates application should exit, true to continue
     * @throws CattisException if command execution or data saving fails
     */
    private boolean executeCommand(Command command) throws CattisException {
        boolean shouldExit = command.isExit();
        command.execute(this);
        storage.save(this.taskList);
        return !shouldExit;
    }
}
