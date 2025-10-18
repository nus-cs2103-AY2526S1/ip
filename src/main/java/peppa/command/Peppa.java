package peppa.command;

import peppa.task.TaskList;
import peppa.storage.Storage;
// import peppa.ui.Ui; (no longer needed)

import java.io.IOException;
import java.util.Scanner;


public class Peppa {
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    public Peppa(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            // handle error, maybe log or set tasks to empty
            this.tasks = new TaskList(new java.util.ArrayList<>());
        }
        assert this.tasks != null : "TaskList should not be null after initialization";
        this.parser = new Parser(tasks, storage);
        assert this.parser != null : "Parser should not be null after initialization";
    }

    // Removed run() method, not needed for GUI

    // main() not needed for GUI

    /**
     * Generates a response for the user's chat message using Parser and TaskList
     */
    public String getResponse(String input) {
        assert input != null : "Input to getResponse should not be null";
        return parser.parse(input);
    }
}
