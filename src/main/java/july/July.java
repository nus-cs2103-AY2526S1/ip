package july;

import java.util.ArrayList;

import july.command.Command;
import july.command.CommandRoute;
import july.data.Storage;
import july.error.JulyException;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Main class for the July task management application.
 * This class serves as the entry point and orchestrates the interaction between
 * the user interface, command processing, task management, and data storage components.
 */
public class July {
    private final Storage storage;
    private final ArrayList<Task> tasks;
    private final Ui ui;
    private boolean shouldExit = false;

    /**
     * Constructs a July application instance with the specified storage file path.
     * Initializes the user interface, storage system, and loads existing tasks from the file.
     *
     * @param filePath the path to the file used for storing task data
     */
    public July(String filePath) {
        assert filePath != null : "File path cannot be null";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Processes a user input command and returns the corresponding response.
     *
     * @param input the user input string to be processed
     * @return a string response indicating the result of the command execution
     *         or an error message if the command processing fails
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        try {
            Command c = CommandRoute.parse(input);
            c.execute(tasks, ui, storage);
            storage.save(tasks);
            shouldExit = c.isDone();
            return c.getResponse();
        } catch (JulyException e) {
            return e.getMessage();
        }
    }

    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Main method that serves as the entry point for the July application.
     * Creates a new July instance with a default storage file and starts the application.
     *
     * @param args command line arguments (not used)
     */

    public static void main(String[] args) {
        new July("data/savefile.txt");
    }
}
