package sage.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

import sage.command.Command;
import sage.exception.SageException;
import sage.parser.Parser;
import sage.storage.Storage;
import sage.task.Deadline;
import sage.task.TaskList;
import sage.task.Ui;

/**
 * Represents the main class of the Sage chatbot application.
 * This class initializes the UI, Storage, and TaskList components,
 * and contains the main loop for running the chatbot.
 */
public class Sage {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/sage.txt";

    /**
     * Constructs a new Sage object.
     * Initializes the UI, Storage, and loads tasks from the specified file path.
     * If loading fails, an empty TaskList is created.
     * @param filePath The path to the file where tasks are stored.
     */
    public Sage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SageException e) {
            ui.getLoadingError();
            tasks = new TaskList();
        }

        ArrayList<Deadline> upcomingDeadlines = tasks.getUpcomingDeadlines();
        if (!upcomingDeadlines.isEmpty()) {
            ui.getMessage(ui.getUpcomingDeadlinesMessage(upcomingDeadlines));
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "Input string cannot be null or empty";
        try {
            Command command = Parser.parse(input);
            String response = command.executeAndReturn(tasks, ui, storage);
            storage.save(tasks.getTasks());
            return response;
        } catch (SageException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }
}
