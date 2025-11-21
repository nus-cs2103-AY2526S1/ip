package jamal.ui;

import jamal.command.Command;
import jamal.task.TaskList;
import jamal.util.Parser;
import jamal.util.Storage;

/**
 * Main App
 * Run Jamal chatbot from here
 *
 */
public class Jamal {

    /**
     * Storage stores filepath for task data
     * Tasklist stores an Arraylist of task to reference
     * Ui stores methods for input scanning and print statements
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Jamal instance through filePath
     * Instantiate UI class for text-UI based prompts if boolean condition met
     *
     * @param filePath datafile of tasks
     * @param isGui true if using GUI to instantiate, false to use text-UI
     */
    public Jamal(String filePath, boolean isGui) {
        if (!isGui) {
            this.ui = new Ui();
        }
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the program
     * Initialises Ui scanner for input detection
     * Converts input into Commands by parsing input
     * Executes Commands while referencing tasklist, ui and storage
     *
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                ui.showMessage(c.execute(tasks, storage));
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (Exception e) {
            return e.getMessage() + " Type help to see list of commands!";
        }
    }

    /**
     * Runs the program for text-based UI
     */
    public static void main(String[] args) {
        new Jamal("data/jamal.ui.Jamal.txt", false).run();
    }
}
