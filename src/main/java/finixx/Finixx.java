package finixx;

import java.io.IOException;

import finixx.parser.Parser;
import finixx.storage.Storage;
import finixx.task.TaskCollection;
import finixx.ui.Ui;

/**
 * The Finixx class represents the main application for managing tasks.
 * It handles user interactions, task storage, and task management.
 */
public class Finixx {
    private Storage storage;
    private TaskCollection tasks;
    private Ui ui;

    /**
     * Constructs a Finixx instance with the specified file path for storage.
     * Initializes the UI, storage, and loads tasks from the file.
     *
     * @param filePath Path to the storage file
     */
    public Finixx(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = loadsTaskFromStorage(storage);

        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "TaskCollection should not be null";

    }

    private TaskCollection loadsTaskFromStorage(Storage storage) {
        try {
            TaskCollection tasks = storage.loadTasks();
            ui.showLoadingSuccess();
            return tasks;
        } catch (IOException e) {
            ui.showLoadingError();
            return new TaskCollection();
        }
    }

    /**
     * Processes user input and returns the corresponding response string.
     *
     * @param input The user input string
     * @return Response string to be displayed in the GUI
     */
    public String getResponse(String input) {
        return Parser.parse(input, ui, tasks, storage);
    }
}
