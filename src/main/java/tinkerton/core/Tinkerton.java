package tinkerton.core;

import tinkerton.util.Ui;
import tinkerton.util.Parser;
import tinkerton.command.Command;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Main class for the Tinkerton application. Handles initialization, user interaction, and command
 * execution loop.
 */
public class Tinkerton {
    /** Handles saving and loading tasks. */
    private Save save;
    /** The list of tasks managed by the application. */
    private TaskList tasks;
    /** Handles user input and output. */
    private Ui ui;

    /**
     * Constructs a Tinkerton instance with the specified file path for saving tasks.
     *
     * @param filePath The path to the save file.
     */
    public Tinkerton(String filePath) {
        assert (filePath != null && !filePath.isEmpty()) : "File path should not be null or empty";
        this.save = new Save(filePath);
        this.tasks = save.load();
        this.ui = new Ui();
    }

    /**
     * Runs the main application loop, handling user commands until exit.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, save);
        } catch (TinkertonException e) {
            return e.getMessage();
        }
    }
}
