package floydai;

import java.io.IOException;

import floydai.command.Command;
import floydai.parser.Parser;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.MainWindow;
import floydai.ui.UI;

/**
 * Main class for the FloydAI chatbot application.
 * Responsible for initializing the application, handling user input,
 * and executing commands in a loop until exit.
 */
public class FloydAI {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    /**
     * Constructs a FloydAI instance with the given file path for storage.
     * Loads tasks from storage or starts with an empty task list if loading fails.
     *
     * @param filePath path to the save file
     */
    public FloydAI(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Injects the {@link MainWindow} instance into the UI,
     * allowing it to render messages in the JavaFX interface
     * instead of (or in addition to) the console.
     *
     * @param window The {@code MainWindow} controller to connect with the UI.
     */
    public void setMainWindow(MainWindow window) {
        this.ui.setMainWindow(window);
    }

    /**
     * Displays the initial welcome message to the user.
     * <p>
     * Delegates to {@link UI#showWelcome()}, which adapts
     * output to either console or JavaFX depending on the setup.
     * </p>
     */
    public void showWelcomeMessage() {
        this.ui.showWelcome();
    }

    /**
     * Runs the main loop of the chatbot, reading user commands and executing them.
     * Continues until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FloydException | IOException e) {
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * Processes a single line of user input by parsing and executing it.
     * <p>
     * - Parses the raw input string into a {@link Command}. <br>
     * - Executes the command with access to the current task list,
     *   the {@link UI} for user interaction, and the {@link Storage}
     *   for persistence. <br>
     * - Commands may modify tasks, display messages, or trigger storage updates.
     * </p>
     *
     * @param input The raw user input string to process.
     * @throws FloydException If the input cannot be parsed or the command fails.
     * @throws IOException    If an error occurs while saving or loading data.
     */
    public void handleInput(String input) throws FloydException, IOException {
        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);
    }

    /**
     * Entry point of the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new FloydAI("./data/FLOYDAI.txt").run();
    }
}
