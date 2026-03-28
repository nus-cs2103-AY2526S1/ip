package lenny.logic;

import lenny.logic.command.Command;
import lenny.logic.command.SupportsPriority;
import lenny.logic.exception.LennyExceptions;
import lenny.logic.parser.Parser;
import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * The main class of the Lenny application.
 * <p>
 * Lenny is a task manager that supports both a Command-Line Interface (CLI)
 * and a Graphical User Interface (GUI). This class is responsible for
 * initializing core components such as the {@link Storage}, {@link TaskList},
 * and {@link Ui}, and provides entry points for both CLI and GUI modes.
 */
public class Lenny {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final boolean guiMode;


    /**
     * Creates a new instance of Lenny, initializing storage, task list, and UI.
     *
     * @param filePath The file path used by {@link Storage} to load and save tasks.
     */
    public Lenny(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            storage.ensureFile();
            tasks = new TaskList(storage.load());
        } catch (RuntimeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        guiMode = true;
    }

    /**
     * Creates a new instance of Lenny, initializing storage, task list, and UI in GUI mode.
     *
     * @param filePath The file path used by {@link Storage} to load and save tasks.
     * @param guiMode A boolean to indicate guiMode or not.
     */
    public Lenny(String filePath, boolean guiMode) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            storage.ensureFile();
            tasks = new TaskList(storage.load());
        } catch (RuntimeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        this.guiMode = guiMode;
    }

    /**
     * Runs the program in Command-Line Interface (CLI) mode.
     * <p>
     * Displays a welcome message, continuously reads user commands from
     * the console, parses them into {@link Command} objects, executes them,
     * and prints the corresponding responses. The loop terminates when an
     * exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                assert fullCommand != null : "User input must not be null";


                Command c = Parser.parse(fullCommand);
                if (c instanceof SupportsPriority) {
                    int priority = ui.readPriority();
                    ((SupportsPriority) c).setPriority(priority);
                }
                assert c != null : "Parser must return a valid command";

                String response = c.execute(tasks, storage, ui);
                assert response != null : "Command execution must return a response";

                ui.showResponse(response);
                isExit = c.isExit();
            } catch (LennyExceptions e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs the program in Graphical User Interface (GUI) mode.
     * <p>
     * Accepts a single user input, parses it into a {@link Command}, executes it,
     * and returns the response as a string. This method is called repeatedly by
     * the GUI controller to handle user interactions.
     *
     * @param input The raw user input string.
     * @return The program's response as a string.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof SupportsPriority) {
                int priority = ui.readPriority();
                ((SupportsPriority) c).setPriority(priority);
            }
            return c.execute(tasks, storage, ui);
        } catch (LennyExceptions e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the program in Graphical User Interface (GUI) mode.
     * <p>
     * Accepts a single user input and a priority, parses it into a {@link Command}, executes it,
     * and returns the response as a string. This method is called repeatedly by
     * the GUI controller to handle user interactions.
     *
     * @param input The raw user input string.
     * @param priority The integer representing priority for a given task.
     * @return The program's response as a string.
     */
    public String getResponse(String input, int priority) {
        try {
            Command c = Parser.parse(input);
            ((SupportsPriority) c).setPriority(priority);
            assert c != null : "Parser must return a valid command";

            return c.execute(tasks, storage, ui);
        } catch (LennyExceptions e) {
            return e.getMessage();
        }
    }

    /**
     * The entry point for running the application in CLI mode.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Lenny("data/LennyData.txt").run();
    }
}
