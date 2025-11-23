package conversal;

import conversal.command.Command;
import conversal.exception.ConversalException;
import conversal.parser.Parser;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Entry point of the Conversal chatbot application.
 *
 * Initialises core components (UI, storage, task list) and runs
 * the main input-processing loop until the user exits.
 * Also displays a GUI-friendly single-turn API.
 *
 */
public class Conversal {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * CLI constructor.
     */
    public Conversal(String filePath) {
        this.ui = new Ui(); // Create new user interface object
        this.storage = new Storage(filePath); // Create storage object
        this.tasks = new TaskList(storage.load());
    }

    /**
     * GUI constructor (used by JavaFX MainApp).
     *
     * @param ui GUI-aware UI implementation
     * @param storage storage component
     * @param tasks in-memory task list
     */
    public Conversal(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Runs the chatbot main loop (CLI mode).
     */
    public void run() {
        ui.welcomeMessage(); // Print welcome message

        while (true) {
            String input = ui.readInput(); // Read user input
            try {
                Command cmd = Parser.parse(input); // Parse input
                cmd.execute(tasks, storage, ui); // Execute command
                if (cmd.isExit()) {
                    break; // Exit from while loop
                }
            } catch (ConversalException e) {
                ui.printError(e.toString()); // Print error message
            }
        }

        ui.exitMessage(); // Print exit message
        ui.close(); // Close scanner
    }

    /**
     * Processes a single line of input and returns the response text (GUI mode).
     *
     * @param input user input line
     * @return text to display in the GUI
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            cmd.execute(tasks, storage, ui);

            if (ui instanceof conversal.ui.GuiUi) {
                return ((conversal.ui.GuiUi) ui).flush();
            }

            return "OK.";
        } catch (ConversalException e) {
            return e.toString();
        }
    }

    public Ui getUi() {
        return ui;
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Application entry point (CLI mode).
     *
     * @param args CLI arguments (ignored)
     */
    public static void main(String[] args) {
        new Conversal("./data/tasks.txt").run();
    }
}
