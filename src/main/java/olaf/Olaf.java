package olaf;

import java.util.ArrayList;
import olaf.tasks.TaskList;

/**
 * Main controller class for Olaf application modified to provide getResponse for GUI.
 */
public class Olaf {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Olaf(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.load(), this.storage);
        } catch (Exception e) {
            ui.showError("Failed to load data. Starting with an empty list.");
            tasks = new TaskList(new ArrayList<>(), this.storage);
        }
    }

    /**
     * Processes a user input command and returns the bot's response as a string.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, tasks);
        } catch (OlafException e) {
            return e.getMessage();
        }
    }
}
