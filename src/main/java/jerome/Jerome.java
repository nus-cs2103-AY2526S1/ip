package jerome;

import jerome.command.Command;
import jerome.storage.Storage;
import jerome.ui.Ui;
import jerome.util.JeromeException;
import jerome.util.Parser;

/**
 * The main application class for the Jerome chatbot.
 * Handles the setup of core components (UI, storage, task list),
 * and runs the main command execution loop.
 */
public class Jerome {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new Jerome chatbot instance.
     * Initializes storage, UI, and loads tasks from disk.
     */
    public Jerome() {
        this.storage = new Storage();
        this.ui = new Ui();
        storage.makeExist();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main command loop:
     * - Reads user input
     * - Parses and executes commands
     * - Handles errors and exit logic
     */
    public void run() {
        ui.welcomeText();
        boolean isExit = false;

        while (!isExit) {
            try {
                String response = ui.readCommand();
                Command c = Parser.parse(response);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JeromeException e) {
                ui.showError((e.getMessage()));
            }
        }
        ui.close();
    }

    /**
     * Returns Jerome's response to user input.
     *
     * @param input The user's input string.
     * @return Jerome's reply as a string.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (JeromeException e) {
            return e.getMessage(); // Show error in GUI
        }
    }

    public static void main(String[] args) {
        new Jerome().run();
    }
}
